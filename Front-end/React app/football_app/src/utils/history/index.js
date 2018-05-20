import createBrowserHistory from 'history/createBrowserHistory';

export default createBrowserHistory({
  basename: '/',
  forceRefresh: !('pushState' in window.history),
});
