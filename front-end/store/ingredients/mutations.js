export const getIngredients = (state, { ingredients }) => {
  state.ingredients = ingredients;
};

export const getUserIngredients = (state, { userIngredients }) => {
  state.userIngredients = userIngredients;
};
