import Adagios from 'adagios';
let worker = new Adagios.WebWorkers.AjaxWebWorker(self, {
  onGetTodoList: (e) => {
    worker.ajax({
      method: 'GET',
      url: '/api/v1/todos',
      mime: 'application/json',
      authorization: e.data.payload.token,
      on: {
        '200': (req) => {
           e.data.payload.todos = JSON.parse(req.responseText);
           worker.work(e.data.work, e.data.payload)
        },
        '*': (req) => {
          console.log('onGetTodoList: ', req.responseText);
        }
      }
    });
  },
  onPutTodo: (e)  => {
    worker.ajax({
      method: 'PUT',
      url: '/api/v1/todo',
      mime: 'application/json',
      authorization: e.data.payload.token,
      data: JSON.stringify(e.data.payload.todo),
      on: {
        '200': (req) => {
          e.data.payload = JSON.parse(req.responseText);
          worker.work(e.data.work, e.data.payload)
        },
        '*': (req) => {
          console.log('onPutTodo: ', req.responseText);
        }
      }
    });
  }
});
export default worker;
