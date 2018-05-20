// @flow
import moment from 'moment';
import { createSelector } from 'reselect';

export const getState = (state: Object) => state;
export const getAddressList = (state: Object) => state.sidebar.list;
export const getHouseId = state => state.sidebar.houseId;
export const getHouseData = state => state.entrance.data;

const getRoomInfo = state => state.devices.roomInfo;
const getDeviceData = state => state.devices.data;
// const getProps = (props: Object) => props;

export const getActiveCounterType = createSelector(
  getState,
  (state: {
    counters: {
      types: Array<Object>,
      active: number,
    },
  }) => state.counters.types.filter(counter => counter.id === state.counters.active)[0],
);

export const getStatisticDates = createSelector(
  getState,
  (state: { statistic: { dates: Object } }) => ({
    from: state.statistic.dates.from.format().split('+')[0],
    to: state.statistic.dates.to.format().split('+')[0],
  }),
);

export const getDevicePayloadValues = createSelector(
  getDeviceData,
  (data: Array<{
      totalVal: Object,
      lastPeriodVal: Object,
      devicePayloadResponse: Object,
    }>): Array =>
    data.map((item) => {
      if ((item.totalVal && item.lastPeriodVal) || item.devicePayloadResponse) return item;
      return {
        ...item,
        totalVal: {
          payloads: {},
        },
        lastPeriodVal: {
          payloads: {},
        },
        devicePayloadResponse: {
          payloads: {},
        },
      };
    }),
);

export const getPayloadValues = ({
  entrance: { data },
}: {
  entrance: {
    data: Array<{
      totalVal: Object,
      lastPeriodVal: Object,
      devicePayloadResponse: Object,
    }>,
  },
}): Array =>
  data.map((item) => {
    if ((item.totalVal.length && item.lastPeriodVal.length) || item.devicePayloadResponse) {
      return item;
    }
    return {
      ...item,
      totalVal: {
        payloads: {},
      },
      lastPeriodVal: {
        payloads: {},
      },
      devicePayloadResponse: {
        payloads: {},
      },
    };
  });

export const getEntranceError = (state: { entrance: { error: boolean } }) => state.entrance.error;

export const getPayloadsSum = () =>
  // array: Array<{ payloads: Object }>, [val, type]: Array<string>
  0;
// array
//   .reduce((prev, curr) => {
//     if (!val) return prev + curr.payloads[type];
//     if (curr[val]) return prev + curr[val].payloads[type];
//     return 0;
//   }, 0)
//   .toFixed(3);

export const getTariffsSum = () => 0;
//   tariffs: Array<Object>,
//   val: string,
// ): {
//   tariff_1: string,
//   tariff_2: string,
//   tariff_3: string,
//   tariff_4: string,
// } =>
// ({
//   tariff_1: tariffs
//     .reduce((prev, curr) => {
//       if (!val) {
//         return prev + (curr.payloads.tariff_1 || curr.payloads.month_rate_tariff_1);
//       }
//       if (curr[val]) {
//         return prev + (curr[val].payloads.tariff_1 || curr[val].payloads.month_rate_tariff_1);
//       }
//       return 0;
//     }, 0)
//     .toFixed(3),
//   tariff_2: tariffs
//     .reduce((prev, curr) => {
//       if (!val) {
//         return prev + (curr.payloads.tariff_2 || curr.payloads.month_rate_tariff_2);
//       }
//       if (curr[val]) {
//         return prev + (curr[val].payloads.tariff_2 || curr[val].payloads.month_rate_tariff_2);
//       }
//       return 0;
//     }, 0)
//     .toFixed(3),
//   tariff_3: tariffs
//     .reduce((prev, curr) => {
//       if (!val) {
//         return prev + (curr.payloads.tariff_3 || curr.payloads.month_rate_tariff_3);
//       }
//       if (curr[val]) {
//         return prev + (curr[val].payloads.tariff_3 || curr[val].payloads.month_rate_tariff_3);
//       }
//       return 0;
//     }, 0)
//     .toFixed(3),
//   tariff_4: tariffs
//     .reduce((prev, curr) => {
//       if (!val) {
//         return prev + (curr.payloads.tariff_4 || curr.payloads.month_rate_tariff_4);
//       }
//       if (curr[val]) {
//         return prev + (curr[val].payloads.tariff_4 || curr[val].payloads.month_rate_tariff_4);
//       }
//       return 0;
//     }, 0)
//     .toFixed(3),
// });

// export const getArrayOfTriggering = createSelector(getProps, (props) => {
//   const minutesArr = new Array(60);
//   console.log(props);
//   // for (let i = 0; i <= 59; i += 1) {
//   //   minutesArr.push({
//   //     id: i,
//   //     time: moment()
//   //       .utcOffset(3)
//   //       .subtract(props.dev.pollingInterval, 'minutes'),
//   //   });
//   // }
//   return minutesArr.reverse();
// });

// export const getAgoDateFromUpdateTime = createSelector(getProps, (props) => {
//   console.log(props.dev);
//   const ago = moment().minutes(props.dev.pollingInterval * 60);
//   console.log('raznica', moment() - ago);
//   const minutes = props.dev.pollingInterval * 60;
//   const agoDate = moment()
//     .utcOffset(3)
//     .subtract(minutes, 'minutes')
//     .format();
//   console.log('ago', agoDate);
//   return ago;
// });

export const getOwnerAddress = createSelector(
  getRoomInfo,
  (rowAddr: Object) =>
    (Object.keys(rowAddr).length
      ? `г. ${rowAddr.entrance.house.street.city.fullname} ул.${rowAddr.entrance.house.street
        .name} ${rowAddr.entrance.house.houseNumber} ${rowAddr.roomType
        .shortname} ${rowAddr.number}`
      : ''),
);



export const getConfirmAnswer = ({ confirm: { answer } }) => answer;
