export default class AbstractPlace {
  constructor(options) {
    this.app = options.app;
  }
  onEntry() {
  }
  onExit() {
    return new Promise((resolve, reject) => {
      resolve();
    });
  }
}
