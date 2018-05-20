// @flow
import React from 'react';

import './style.css';

const Select = ({
  width,
  height,
  style,
  // selected,
  options,
  handler,
  defaultValue,
}: {
  width: number,
  height: number,
  style: Object,
  // selected: number,
  options: Array<{ text: string, value: string }>,
  defaultValue: number | string,
  handler: Function,
}) => (
  <select
    onChange={handler}
    style={{ ...style, width: `${width}px`, height: `${height}px` }}
    className="select-element"
    defaultValue={defaultValue}
  >
    {options.map(option => (
      <option
        key={option.value}
        value={option.value}
        // defaultValue={selected === option.value ? 'selected' : ''}
      >
        {option.text}
      </option>
    ))}
  </select>
);

export default Select;
