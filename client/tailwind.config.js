/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,ts}"],
  theme: {
    extend: {
      colors: {
        primary: "#11111",
        secondary: "#f5f5f5"
      },
      fontFamily: {
        primary: "Rationale",
      },
    },
  },
  plugins: [],
};
