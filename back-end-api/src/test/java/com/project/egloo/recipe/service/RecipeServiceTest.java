package com.project.egloo.recipe.service;

import com.project.egloo.recipe.domain.Category;
import com.project.egloo.recipe.domain.Difficulty;
import com.project.egloo.recipe.domain.Recipe;
import com.project.egloo.recipe.dto.CreateRecipeDTO;
import com.project.egloo.recipe.dto.RecipeDTO;
import com.project.egloo.recipe.exception.CategoryNotFoundException;
import com.project.egloo.recipe.repository.CategoryRepository;
import com.project.egloo.recipe.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    public void createRecipeTest() {
        // given
        Category category = new Category("국");
        category.setId(1L);

        Recipe recipe = new Recipe(
            category,
            "소고기 미역국 레시피",
            "소고기 미역국 레시피 입니다.",
            Difficulty.MIDDLE,
            "https://image.com/recipe1.jpg",
            6000
        );
        recipe.setId(1L);

        CreateRecipeDTO createRecipeInfo = new CreateRecipeDTO(
            "국",
            "소고기 미역국 레시피",
            "소고기 미역국 레시피 입니다.",
            Difficulty.MIDDLE,
            "https://image.com/recipe1.jpg",
            6000
        );

        // when
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);
        when(categoryRepository.findByName("국")).thenReturn(Optional.of(category));

        RecipeDTO createdRecipe = recipeService.createRecipe(createRecipeInfo);

        // then
        assertThat(createdRecipe.getCategory().getName()).isEqualTo("국");
        assertThat(createdRecipe.getName()).isEqualTo("소고기 미역국 레시피");
        assertThat(createdRecipe.getSummary()).isEqualTo("소고기 미역국 레시피 입니다.");
        assertThat(createdRecipe.getDifficulty()).isEqualTo(Difficulty.MIDDLE);
        assertThat(createdRecipe.getImage()).isEqualTo("https://image.com/recipe1.jpg");
        assertThat(createdRecipe.getPrice()).isEqualTo(6000);
        assertThat(createdRecipe.getCountLike()).isEqualTo(0);
        assertThat(createdRecipe.getCountReview()).isEqualTo(0);
        assertThat(createdRecipe.getRatings()).isEqualTo(0.0);
    }

    @Test
    public void createRecipeWithNoCategoryErrorTest() {
        // given
        Category category = new Category("NoCategory");
        category.setId(1L);

        Recipe recipe = new Recipe(
            category,
            "소고기 미역국 레시피",
            "소고기 미역국 레시피 입니다.",
            Difficulty.MIDDLE,
            "https://image.com/recipe1.jpg",
            6000
        );
        recipe.setId(1L);

        // when
        when(categoryRepository.findByName("국")).thenReturn(Optional.empty());
        CreateRecipeDTO createRecipeInfo = new CreateRecipeDTO(
            "국",
            "소고기 미역국 레시피",
            "소고기 미역국 레시피 입니다.",
            Difficulty.MIDDLE,
            "https://image.com/recipe1.jpg",
            6000
        );
        // then
        assertThatThrownBy(() -> recipeService.createRecipe(createRecipeInfo))
            .isInstanceOf(CategoryNotFoundException.class)
            .hasMessage("No category where name: 국");
    }
}