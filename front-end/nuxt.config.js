export default {
  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    title: "front-end",
    htmlAttrs: {
      lang: "en"
    },
    meta: [
      { charset: "utf-8" },
      { name: "viewport", content: "width=device-width, initial-scale=1" },
      { hid: "description", name: "description", content: "" },
      { name: "format-detection", content: "telephone=no" }
    ],
    link: []
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: ["@/assets/css/common.css", "@/assets/css/transition.css"],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
    { src: "~/plugins/vee-validate", mode: "client" },
    { src: "~/plugins/axios", mode: "client" },
    { src: "~/plugins/services" }
  ],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: ["@nuxtjs/vuetify"],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    // https://go.nuxtjs.dev/bootstrap
    "@nuxtjs/axios",
    "@nuxtjs/auth",
    "@nuxtjs/svg"
  ],

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {
    transpile: ["vee-validate/dist/rules"]
  },

  axios: {
    proxy: true
  },
  proxy: {
    "/api": {
      target: "http://localhost:8080",
      pathRewrite: { "^/api": "" }
    },
    changeOrigin: true
  },

  auth: {
    strategies: {
      local: {
        scheme: "refresh",
        token: {
          property: "access_token",
          maxAge: 1800,
          global: true
          // type: "Bearer"
        },
        refreshToken: {
          property: "refresh_token",
          data: "refresh_token",
          maxAge: 60 * 60 * 24 * 30
        },
        user: {
          property: "user",
          autoFetch: true
        },
        endpoints: {
          login: {
            url: "/api/auth/login",
            method: "post",
            headers: {
              "Content-Type": "application/json"
            },
            propertyName: "token"
          },
          user: { url: "/api/auth/user", method: "post", propertyName: "user" },
          logout: false
        }
      }
    }
  }
};
