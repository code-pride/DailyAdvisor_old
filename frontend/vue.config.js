module.exports = {
  devServer: {
    host: '0.0.0.0',
    port: 3000,
    proxy: {
      '/api': {
        target: 'http://localhost:8080/',
        ws: true,
        changeOrigin: true,
      },
    },
  },
};
