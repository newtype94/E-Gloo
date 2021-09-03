<template>
  <div>
    <v-container style="width: 400px">
      <div class="ingredientsPageHeader">내 재료</div>
      <v-tabs fixed-tabs style="color: #e57979">
        <v-tab
          v-for="{ category } in ingredients"
          v-bind:key="category"
          class="ingredientsTabs"
          >{{ category }}</v-tab
        >
        <v-tab-item
          v-for="{ category, content } in ingredients"
          v-bind:key="category"
        >
          <IngredientsGrid
            :category="category"
            :ingredients="content"
          ></IngredientsGrid>
        </v-tab-item>
      </v-tabs>
    </v-container>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import IngredientsGrid from "@/components/IngredientsGrid";

export default {
  components: {
    IngredientsGrid,
  },
  async asyncData({ route, store }) {
    const { query } = route;

    await store.dispatch("ingredients/getIngredients", {}, { root: true });
  },
  data() {
    return {};
  },
  props: {},
  computed: {
    ...mapGetters(["isAuthenticated", "loggedInUser"]),
    ...mapState("ingredients", ["ingredients", "userIngredients"]),
  },
  methods: {},
};
</script>

<style scoped>
.ingredientsPageHeader {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 20px;
  line-height: 29px;
  height: 100px;
  text-align: center;

  color: #4f4f4f;
}
.ingredientsTabs {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 12px;
  line-height: 17px;
  padding: 0px;
  min-width: 60px;

  text-align: center;
  color: #b9b9b9;
}
</style>
