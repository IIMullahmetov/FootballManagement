import React from 'react';
import MaskedInput from 'react-text-mask';

import './style.css';

const DataPicker = ({
  height,
  width,
  placeholder,
  disabled,
  style,
  withoutAlert,
  value,
  mask = [],
  ...props
}: {
  height: number,
  width: number,
  placeholder: string,
  style: Object<string>,
  withoutAlert: boolean,
  disabled: boolean,
  value: string | number,
  mask: Array,
}) => {
  if (mask && mask.length) {
    return (
      <MaskedInput
        type="date"
        style={{
          ...style,
          width: `${width}px`,
          height: `${height}px`,
        }}
        id="input-element"
        placeholder={placeholder}
        disabled={disabled}
        value={value}
        className={`${!value && !withoutAlert ? 'empty' : ''}${disabled ? ' disabled' : ''}`}
        mask={mask}
        {...props}
      />
    );
  }
  return (    
    <input
      type="date"
      style={{
        ...style,
        width: `${width}px`,
        height: `${height}px`,
      }}
      id="input-element"
      placeholder={placeholder}
      disabled={disabled}
      value={value}
      className={`${!value && !withoutAlert ? 'empty' : ''}${disabled ? ' disabled' : ''}`}
      {...props}
    />
  );
};
export default DataPicker;
