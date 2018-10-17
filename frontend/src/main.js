import Handstand from 'handstand';
import Adagios from 'adagios';
import Session from './places/Session.js';
import Home from './places/Home.js';
import Error from './places/Error.js';
export default class App extends Handstand.Orchestrator {
  onContentLoaded() {
    this.dom = {
      body: document.querySelector('body'),
      head: document.querySelector('head')
    }
    this.home = new Home({
      app: this,
      worker: new Adagios.WebWorker('/todo-worker.min.js', {})
    });
    this.session = new Session({
      app: this,
      worker: new Adagios.WebWorker('/session-worker.min.js', {}),
      onSessionInitiate: (e) => {
        this.go(this.home);
      }
    });
    this.place = this.session;
    this.session.initiate();
  }
  go(place) {
    this.place.onExit().then(
      () => {
        this.place = place;
        this.place.onEntry();
      },
      (e) => {
        this.place = new Error();
        this.place.onEntry(e)
      }
    );
  }
}
