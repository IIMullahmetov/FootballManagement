// @flow
import React from 'react';

import './style.css';

const Button = ({
  width,
  height,
  style,
  text,
  title,
  handler,
}: {
  width: number,
  height: number,
  text: string,
  title: string,
  style: Object,
  handler: Function,
}) => (
  <span
    className="button-element"
    role="button"
    style={{
      ...style,
      width: `${width}px`,
      height: `${height}px`,
    }}
    tabIndex={0}
    onClick={handler}
    onKeyPress={handler}
    title={title}
  >
    {text}
  </span>
);

export default Button;
