import request from 'superagent-defaults';
import prefix from 'superagent-prefix';
import use from 'superagent-use';

const headers = {
  Accept: 'application/json',
  'Content-Type': 'application/json',
  'X-Requested-With': 'XMLHttpRequest',
};

const agent = () => {
  const defaults = request().set({
    ...headers,
    Authorization: `Bearer ${localStorage.getItem('access') || ''}`,
  });

  return use(defaults).use(prefix('http://footballmanagement.azurewebsites.net'));
};

export default agent;
