import { createAction } from 'redux-act';

export default {
  open: createAction('open modal', (modal: string, payload: Object): Object => ({
    modal,
    payload: payload || {},
  })),
  close: createAction('close modal', (modal: string) => ({ modal })),
  set: createAction(
    'set modal property',
    (event: { value: string, dataset: { prop: string } }, modal: string) => ({ event, modal }),
  ),
  setChecked: createAction('set checkbox value', (index: number) => ({ index })),
  setSmsKey: createAction('set sms key', (event: Object) => ({ event })),
  error: createAction('open error modal', (text: string) => ({ text })),
  alert: createAction('open alert', (text: string) => ({ text })),
  confirm: createAction('confirm action'),
};
