# Thuuz Excitement Algorithm

This document outlines how Thuuz computes game excitement ratings and categorizes them into different rating thresholds.

## Global Excitement (Gex) Score Calculation

The Global Excitement (Gex) score ranges up to 100 and represents the overall excitement level of a game. It is calculated differently depending on whether the game is upcoming or currently active/completed.

### Upcoming Games
For upcoming games, the system uses a predicted excitement score (`gex_predict`).
- **Base Score:** The base score is taken from the `gex_predict` property.
- **Fan Adjustment:** If the user has favorite teams playing in the game, the system adjusts the predicted score upwards. The adjusted score is the maximum of:
  - The base predicted score + 10
  - `(base predicted score / 10) + 55`

### Active and Completed Games
For games that are in progress or completed, the system uses a live or final excitement score (`gex`).
- **Base Score:** The base score is taken from the `gex` property.
- **Fan Adjustment:** If the user has favorite teams playing in the game, a fan adjustment is added to the base score.
  - If the user is a fan of the home team, `gex_home_fan_adjust` is added.
  - If the user is a fan of the away team, `gex_away_fan_adjust` is added.
  - If the user is a fan of both the home and away teams, the maximum of `gex_home_fan_adjust` and `gex_away_fan_adjust` is added.

*Note: In all cases, the final calculated Gex score is capped at a maximum of 100.*

## Rating Thresholds

Once the final numerical Gex score is determined, it is mapped to a specific `GAME_RATING` threshold. The mapping is as follows:

- **Great Game (`RATING_GREAT`):** Gex score of **85** or higher.
- **Good Game (`RATING_GOOD`):** Gex score between **65** and **84**.
- **OK Game (`RATING_OK`):** Gex score between **40** and **64**.
- **Dull Game (`RATING_DUD`):** Gex score below **40**.
