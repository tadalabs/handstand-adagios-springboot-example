var pkg = require('./package.json');
module.exports = {
  module: {
    rules: [
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader'],
      }
    ],
  },
  entry: {
    bundle: './index.js',
    'session-worker': './src/workers/SessionAjaxWebWorker.js',
    'todo-worker': './src/workers/TodoAjaxWebWorker.js'
  },
  output: {
    path: __dirname + '/dist',
    publicPath: '/',
    filename: '[name].min.js',
  },
  devServer: {
    contentBase: './dist',
    // proxy backend requests to springboot server
    proxy: {
      '/api/v1/**': {
          target: 'http://localhost:8080',
          secure: false,
          pathRewrite: function(req, path) {
           return path;
          }
      }
    }
  }
};
