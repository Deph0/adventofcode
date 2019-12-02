'use strict'

// Part 1
// ======

const part1 = input => {
  // input  = 9
  // the first recipe got a score of 3, the second, 7.
  // Each of the two Elves has a current recipe:
  //   - the first Elf starts with the first recipe   =[i]
  //   - the second Elf starts with the second recipe =[i+1]
  
  // Elf steps forward through the scoreboard a number of recipes equal to 1 plus the score of their current recipe
  let recipe_scores = [3,7]//[2,3]=[2,3,5]
  let elf_onerecipe_index = 0
  let elf_two_recipe_index = 1

  while (recipe_scores.length - 10 < input) { // we want the 10 output after input, so overshoot input by 10
    let elf_one_score = recipe_scores[elf_onerecipe_index];
    let elf_two_score = recipe_scores[elf_two_recipe_index];
      
    const current_recipe_score = elf_one_score + elf_two_score;
    
    if (current_recipe_score > 9) {
      var r1 = parseInt( current_recipe_score / 10 ) // 0-9
      var r2 = current_recipe_score % 10 // rest

      recipe_scores.push( r1 )
      recipe_scores.push( r2 )
    }
    else {
      // below 10
      recipe_scores.push( current_recipe_score % 10 )
    }

    elf_onerecipe_index = (elf_onerecipe_index +  1 + elf_one_score) % recipe_scores.length
    elf_two_recipe_index = (elf_two_recipe_index +  1 + elf_two_score) % recipe_scores.length
  }
  
  // get 10 scores after input data
  // take the 10 scores after input was found
  // return JSON.stringify( recipe_scores.splice(input,10))

  // return recipe_scores.splice(input,10).join('') // = 1052903161 
  return recipe_scores // for pt2
}

// Part 2
// ======

const part2 = input => {
  const recipe_scores = part1(input)
  // return  JSON.stringify(recipe_scores.slice(0, input)) // + JSON.stringify( recipe_scores.splice(input,10))
  // return "index: "  + recipe_scores.length - input.length // = 440236
  // tried "recipe_scores.slice(0, input).length" which gave me "440231"- AOC said #wrong & too low number (lol, that equals to my input. derp.)  

  // From examples:
  // return recipe_scores.splice(9, 5).join('') // = 51589
  // return recipe_scores.splice(5, 5).join('') // = 01245
  // return recipe_scores.splice(18, 5).join('') // = 92510
  // return recipe_scores.splice(2018, 5).join('') // = 59414

  // return recipe_scores.splice(input, input.length).join('') // = 105290, incorrect

  return '51589'.split('')
  return recipe_scores
  
}

module.exports = { part1, part2 }
