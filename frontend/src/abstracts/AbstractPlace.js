export default class AbstractPlace {
  constructor(options) {
    this.app = options.app;
  }
  go() {
  }
  leave() {
    return new Promise((resolve, reject) => {
      resolve();
    });
  }
}
