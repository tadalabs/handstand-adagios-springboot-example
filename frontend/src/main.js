import Handstand from 'handstand';
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
      app: this
    });
    this.session = new Session({
      app: this,
      onSessionInitiate: (e) => {
        this.go(this.home);
      }
    });
    this.place = this.session;
    this.session.initiate();
  }
  go(place) {
    this.place.leave().then(
      () => {
        this.place = place;
        this.place.go();
      },
      (e) => {
        this.place = new Error();
        this.place.go(e)
      }
    );
  }
}
