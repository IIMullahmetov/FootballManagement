// @flow
import { put, takeLatest, select, call } from 'redux-saga/effects';
import { mapA, modalsA } from 'actions';
import map from 'api/map';

import { getAddressList } from 'selectors';

export function* fetchHouses(): Object {
  try {
    const { body }: { body: Array } = yield call(map.getHouses);

    yield put(mapA.housesSuccess(body));

    const house: Object = yield call(map.getCity, 'json', body[0].regionName, body[0].cityName);
    const pos: Array<number> = [+house.body[0].lat, +house.body[0].lon];

    yield put(mapA.findHouseSuccess(pos));
  } catch (error) {
    yield put(mapA.housesError(error));
  }
}

export function* fetchFindHouseMarker({
  payload: {
    id,
    // state,
    city,
    street,
  },
}: {
  payload: {
    id: string,
    // state: string,
    city: string,
    street: string,
  },
}): Object {
  try {
    const geocode = `${city},+${street.split(',')[1]}+улица,+дом+${street.split(',')[0]}`;
    const { body: { response } }: { body: { response: Object } } = yield call(
      map.getHouseMarker,
      'json',
      geocode,
    );
    const pos = response.GeoObjectCollection.featureMember[0].GeoObject.Point.pos.split(' ');
    yield put(mapA.setAddressSuccess({
      ...pos,
      city,
      street: street.split(',').reverse(),
      id,
    }));
  } catch (error) {
    yield put(mapA.setAddressError(error));
  }
}

export function* fetchMouseClickAddress({
  payload: { address },
}: {
  payload: { address: Object },
}): Object {
  try {
    const coords = address.get('coords');
    const houses: Array<Object> = yield select(getAddressList);

    const { body: { response } }: { body: { response: Object } } = yield call(
      map.getMapClickAddress,
      coords.toString(),
    );
    const components =
      response.GeoObjectCollection.featureMember[0].GeoObject.metaDataProperty.GeocoderMetaData
        .Address.Components;

    const street: string = components[5].name.replace(/улица\s/gm, '');
    const house: Object = houses.filter(item =>
      item.cityName === components[4].name &&
        item.streetName === street &&
        item.houseNumber === components[6].name)[0];

    if (house) {
      yield put(mapA.setAddressSuccess({
        ...coords.reverse(),
        city: house.cityName,
        street: `${house.streetName}, ${house.houseNumber}`,
        id: house.houseId,
      }));
    } else {
      yield put(modalsA.alert('Такого дома в списке нет.'));
    }
  } catch (error) {
    yield put(mapA.setAddressError(error));
  }
}

export default function* watchFindHouseMarker(): Object {
  yield takeLatest(mapA.housesPending.getType(), fetchHouses);
  yield takeLatest(mapA.setAddressPending.getType(), fetchFindHouseMarker);
  yield takeLatest(mapA.clickOnMap.getType(), fetchMouseClickAddress);
}
