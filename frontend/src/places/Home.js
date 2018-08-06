import '../css/Home.css';
import Adagios from 'adagios';
import Handstand from 'handstand';
import AbstractPlace from '../abstracts/AbstractPlace.js';
import TodoList from '../elements/TodoList.js';
export default class Home extends AbstractPlace {
  constructor(options) {
    super(options);
    this.todos = options.todos || [];
    this.worker = options.worker;
    this.worker.ready({
      'onGetTodoList': this.onGetTodoList.bind(this)
    });
  }
  onEntry() {
    this.toolBar = new Handstand.Elements.Container({
      css: 'todo-toolbar'
    });
    this.app.dom.body.append(this.toolBar);
    this.toolBar.append(this.app.session.identity())
    this.worker.work('onGetTodoList', {
      token: this.app.session.identity() });
  }
  onExit() {
  }
  onGetTodoList(e) {
    this.todos = e.data.payload.todos;
    this.list = new TodoList({
      css: 'todo-list-element',
      properties: {
        app: this.app,
        worker: this.worker,
        items: this.todos
      }
    });
    this.list.newListItem();
    this.app.dom.body.append(this.list);
  }
}
