export const API_URL = "http://localhost:8080";

export const apiEndpoint = {
  AuthEndpoint: {
    authenticate: `${API_URL}/api/v1/auth/authenticate`,
    register: `${API_URL}/api/v1/auth/register`
  },
  ProductEndpoint: {
    base: `${API_URL}/api/v1/product`,
    getById: `${API_URL}/api/v1/product`,
  },
  CategoryEndpoint: {
    base: `${API_URL}/api/v1/category`,
    getByReferenceName: `${API_URL}/api/v1/category`,
  },
  CartEndpoint: {
    base: `${API_URL}/api/v1/cart`,
    remove: `${API_URL}/api/v1/cart/remove`
  }
}