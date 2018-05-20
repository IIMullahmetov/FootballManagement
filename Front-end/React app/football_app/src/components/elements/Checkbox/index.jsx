import React from 'react';

import './style.css';

const Checkbox = ({ text, checked, ...props }: { text: string, checked: boolean }) => (
  <label htmlFor="input" className="checkbox-element" {...props}>
    {text}
    <input type="checkbox" checked={checked} />
    <span className="checkmark" />
  </label>
);

export default Checkbox;
