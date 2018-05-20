import React from 'react';
import renderer from 'react-test-renderer';
import { shallow } from 'enzyme';

import InputText from './index';

describe('custom input', () => {
  let wrapper;
  let snapshootWrapper;

  beforeEach(() => {
    wrapper = shallow(<InputText width={200} height={30} placeholder="test" />);
    snapshootWrapper = renderer.create(<InputText width={200} height={30} placeholder="test" />);
  });

  it('correct render', () => {
    expect(snapshootWrapper).toMatchSnapshot();

    expect(wrapper.prop('placeholder')).toEqual('test');
    expect(wrapper.prop('className')).toEqual('empty');
    expect(wrapper.prop('style')).toHaveProperty('width', '200px');
    expect(wrapper.prop('style')).toHaveProperty('height', '30px');
  });

  it('is empty', () => {
    const text = 'test text';
    wrapper.setProps({ value: text });

    expect(wrapper.prop('className')).toEqual('');
  });

  it('is disabled', () => {
    wrapper.setProps({ disabled: true });

    expect(wrapper.prop('className')).toEqual('empty disabled');
    expect(wrapper.prop('disabled')).toEqual(true);
  });
});
