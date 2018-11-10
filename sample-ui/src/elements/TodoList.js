import Handstand from 'handstand';
import TodoListItem from './TodoListItem.js'
import '../css/TodoList.css'
export default class TodoList extends Handstand.CustomElement {
  onCreate() {
    this.app = this.conditions.properties.app;
    this.worker = this.conditions.properties.worker;
    this.list = new Handstand.Elements.Container({
      id: 'todolist-list'
    });
    this.conditions.properties.items.map( (todo) => {
        this.list.append(new TodoListItem({
          properties: {
            item: todo,
            worker: this.worker,
            app: this.app
          }
        }));
    });
    this.button = new Handstand.WrappedElement('i');
    this.button.setAttribute('id', 'new-item-button');
    this.button.on('click', this.newListItem.bind(this));
    this.append(this.button.innerElement, this.list);
  }
  onSelectItem() {
  }
  newListItem() {
    this.list.append(new TodoListItem({
      properties: {
        item: { value: '', state: 'TODO' },
        worker: this.worker,
        app: this.app
      }
    }))
  }
}
customElements.define('todo-list', TodoList);
