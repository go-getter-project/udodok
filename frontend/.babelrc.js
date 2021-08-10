const isDevelopment = process.env.NODE_ENV !== 'production';

module.exports = {
  presets: [
    [
      '@babel/preset-env',
      {
        targets: { browsers: ['last 2 chrome versions'] },
        debug: isDevelopment,
      },
    ],
    '@babel/preset-react',
  ],
};
