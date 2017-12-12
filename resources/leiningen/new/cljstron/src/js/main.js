// import AwesomeComponent  from 'some-library';

window.deps = {
  react: require("react"), //React,
  "react-dom": require("react-dom"), //ReactDOM,
  // 'awesome-component': AwesomeComponent,
};

window.React = window.deps["react"];
window.ReactDOM = window.deps["react-dom"];
