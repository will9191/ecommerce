export const API_URL = "http://localhost:8080";

export const apiEndpoint = {
  AuthEndpoint: {
    authenticate: `${API_URL}/api/v1/auth/authenticate`,
    register: `${API_URL}/api/v1/auth/register`
  },
  productEndpoint: {
    base: `${API_URL}/api/v1/product`,
  }
}