import Adagios from 'adagios'
let worker = new Adagios.WebWorkers.AjaxWebWorker(self, {
  onSessionInitiate: (e) => {
    worker.ajax({
      method: 'GET',
      url: '/api/v1/session/initiate',
      mime: 'application/json',
      authorization: e.data.payload.token,
      on: {
        '200': (req) => {
           e.data.payload = JSON.parse(req.responseText);
           worker.work(e.data.work, e.data.payload)
        },
        '*': (req) => {
          console.log('req:', req.responseText);
        }
      }
    });
  }
});
export default worker;
