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
      'onGetTodoList': this.onGetTodoList.bind(this),
      'onPutTodo': this.onPutTodo.bind(this)
    });
  }
  toolBar() {
    this.toolBar = new Handstand.Elements.
      Container({ css: 'todo-toolbar' });
    this.app.dom.body.append(this.toolBar);
    this.toolBar.append(this.app.session.identity())
  }
  todoList() {
    if (!this.list) {
      this.list = new TodoList();
      this.app.dom.body.append(this.list);
    }
    this.list.update(this.todos)
  }
  getTodoList() {
    this.worker.work('onGetTodoList', {
      token: this.app.session.identity() });
  }
  onGetTodoList(e) {
    this.todos = e.data.payload.todos;
    this.todoList();
  }
  putTodo(todo) {
    this.worker.work('onPutTodo', {
      token: this.app.session.identity(),
      todo: todo });
  }
  onPutTodo(e) {
    this.todoList();
  }
  go() {
    this.toolBar();
    this.getTodoList();
  }
  leave() {
  }
}
