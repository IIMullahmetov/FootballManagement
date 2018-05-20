import React from 'react';

import './style.css';

const CrossClose = ({
  style,
  width,
  height,
  handler,
}: {
  style: Object,
  width: number,
  height: number,
  handler: Function,
}) => (
  <span
    role="button"
    onClick={handler}
    onKeyPress={handler}
    tabIndex={0}
    style={{
      ...style,
      width: `${width}px`,
      height: `${height}px`,
    }}
    className="cross-close"
  />
);

export default CrossClose;
