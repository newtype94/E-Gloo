<template>
  <v-container fluid>
    <v-row>
      <v-col
        v-for="ingredient in ingredients"
        :key="ingredient"
        class="d-flex child-flex"
        cols="3"
        style="padding: 3px"
      >
        <v-img
          :src="'/tobe-url/' + ingredient"
          :lazy-src="'/tobe-url/' + ingredient"
          aspect-ratio="1"
          class="profile_image"
          position="left"
          @click="updateUserIngredients(ingredient)"
        >
          <template v-slot:placeholder>
            <v-row class="fill-height" align="center" justify="center">
              <v-progress-circular
                indeterminate
                color="white"
              ></v-progress-circular>
            </v-row>
          </template>
        </v-img>
        <div class="ingredientHeader">{{ ingredient }}</div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  props: {
    category: {
      type: String,
      default: () => "",
    },
    ingredients: {
      type: Array,
      default: () => [],
    },
  },
  methods: {
    async updateUserIngredients(content) {
      await this.$store.dispatch(
        "ingredients/updateUserIngredients",
        {
          userId: "mh9406", //nuxt auth에서 받아오는 것으로 변경
          category: this.category, //props로 받은 값 그대로 사용
          content,
        },
        { root: true }
      );
    },
  },
};
</script>
<style scoped>
.ingredientHeader {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 10px;
  line-height: 14px;
  text-align: center;

  color: #b9b9b9;
}
</style>
