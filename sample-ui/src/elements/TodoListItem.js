import Handstand from 'handstand';
import '../css/TodoListItem.css';
export default class TodoListItem extends Handstand.CustomElement {
  get value() {
    return this.conditions.properties.item;
  }
  set value(v) {
    this.conditions.properties.item = v
    this.label.value = v.value;
    this.icon.value = v.state;
  }
  onCreate() {
    this.app = this.conditions.properties.app;
    this.label = new Handstand.Elements.MutableLabel({
      css: 'todo-item-value',
      properties: {
        value: this.conditions.properties.item.value
      }
    });
    this.label.on('modeling', this.onChangeValue.bind(this));
    this.icon = new Handstand.WrappedElement('i');
    this.icon.on('click', this.onChangeState.bind(this));
    this.reconcileState(this.conditions.properties.item.state);
    this.append(this.label, this.icon.innerElement);
  }
  reconcileState(state) {
    if (state === 'DONE' || this.icon.innerText === 'check_circle_outline') {
      this.value.state = 'DONE';
      this.icon.innerText = 'check_circle';
      this.icon.setAttribute('class', 'material-icons done-state');
    } else {
      this.value.state = 'TODO';
      this.icon.innerText = 'check_circle_outline';
      this.icon.setAttribute('class', 'material-icons todo-state');
    }
  }
  onChangeState(e) {
    this.reconcileState();
    this.onChangeValue(e);
  }
  onChangeValue(e) {
    this.value.value = this.label.value;
    this.conditions.properties.worker.work('onPutTodo', {
      todo: this.value,
      token: this.app.session.identity()
    });
  }
}
customElements.define('todo-list-item', TodoListItem);
