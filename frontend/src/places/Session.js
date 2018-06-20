import Adagios from 'adagios';
import AbstractPlace from '../abstracts/AbstractPlace.js';
export default class Session extends AbstractPlace {
  constructor(options) {
    super(options);
    this.events = {
      onSessionInitiate: options.onSessionInitiate
    };
    this.worker = new Adagios.WebWorker('/session-worker.min.js', {
      'onSessionInitiate': this.onSessionInitiate.bind(this)
    });
  }
  identity() {
    let session = null;
    try {
      session = JSON.parse(localStorage.getItem('session')).Id;
    } catch(x) {
    }
    return session
  }
  initiate() {
    this.worker.work('onSessionInitiate', { token: this.identity() });
  }
  onSessionInitiate(e) {
    localStorage.setItem('session', JSON.stringify(e.data.payload));
    if (typeof this.events.onSessionInitiate === 'function')
      this.events.onSessionInitiate.call(this, e);
  }
}
