<?php
// Fill up array with names
$a[]="Almonds";
$a[]="Apple";
$a[]="Apricot";
$a[]="Asparagus";
$a[]="Avocado";
$a[]="Banana";
$a[]="Barley";
$a[]="Basil";
$a[]="Beef";
$a[]="Beet Greens";
$a[]="Beets";
$a[]="Bell Peppers";
$a[]="Black Beans";
$a[]="Black Pepper";
$a[]="Blueberries";
$a[]="Bok Choy";
$a[]="Broccoli";
$a[]="Brown Rice";
$a[]="Brussels Sprouts";
$a[]="Buckwheat";
$a[]="Cabbage";
$a[]="Cantaloupe";
$a[]="Carrots";
$a[]="Cashews";
$a[]="Cauliflower";
$a[]="Cayenne Pepper";
$a[]="Celery";
$a[]="Cheese";
$a[]="Chicken";
$a[]="Chili Pepper";
$a[]="Cilantro";
$a[]="Cinnamon";
$a[]="Cloves";
$a[]="Cod";
$a[]="Collard Greens";
$a[]="Coriander";
$a[]="Corn";
$a[]="Cranberries";
$a[]="Cucumber";
$a[]="Cumin";
$a[]="Dill";
$a[]="Dried Peas";
$a[]="Eggplant";
$a[]="Eggs";
$a[]="Fennel";
$a[]="Figs";
$a[]="Flax Seeds";
$a[]="Garbanzo Beans";
$a[]="Garlic";
$a[]="Ginger";
$a[]="Grapefruit";
$a[]="Grapes";
$a[]="Green Beans";
$a[]="Green Peas";
$a[]="Green Tea";
$a[]="Honey";
$a[]="Kale";
$a[]="Kidney Beans";
$a[]="Kiwifruit";
$a[]="Lamb";
$a[]="Leeks";
$a[]="Lemons"; 
$a[]="Limes";
$a[]="Lentils";
$a[]="Lima Beans";
$a[]="Limes";
$a[]="Maple Syrup";
$a[]="Milk";
$a[]="Millet";
$a[]="Miso";
$a[]="Molasses";
$a[]="Blackstrap";
$a[]="Mushrooms";
$a[]="Crimini";
$a[]="Mushrooms";
$a[]="Shiitake";
$a[]="Mustard Greens";
$a[]="Mustard Seeds";
$a[]="Navy Beans";
$a[]="Oats";
$a[]="Olive Oil";
$a[]="Olives";
$a[]="Onions";
$a[]="Oranges";
$a[]="Oregano";
$a[]="Papaya";
$a[]="Parsley";
$a[]="Peanuts";
$a[]="Pear";
$a[]="Peppermint";
$a[]="Pineapple";
$a[]="Pinto Beans";
$a[]="Plum";
$a[]="Potatoes";
$a[]="Prunes";
$a[]="Pumpkin Seeds";
$a[]="Quinoa";
$a[]="Raisins";
$a[]="Raspberries";
$a[]="Romaine Lettuce";
$a[]="Rosemary";
$a[]="Rye";
$a[]="Sage";
$a[]="Salmon";
$a[]="Sardines";
$a[]="Scallops";
$a[]="Sea Vegetables";
$a[]="Sesame Seeds";
$a[]="Shrimp";
$a[]="Soy Sauce";
$a[]="Soybeans";
$a[]="Spelt";
$a[]="Spinach";
$a[]="Strawberries";
$a[]="Summer Squash";
$a[]="Sunflower Seeds";
$a[]="Sweet Potato";
$a[]="Swiss Chard";
$a[]="Tempeh";
$a[]="Thyme";
$a[]="Tofu";
$a[]="Tomatoes";
$a[]="Tuna";
$a[]="Turkey";
$a[]="Turmeric";
$a[]="Turnip Greens";
$a[]="Walnuts";
$a[]="Water";
$a[]="Watermelon";
$a[]="Wheat";
$a[]="Winter Squash";
$a[]="Yams";
$a[]="Yogurt";


// get the q parameter from URL
$q=$_REQUEST["q"]; $hint="";

// lookup all hints from array if $q is different from ""
if ($q !== "")
  { $q=strtolower($q); $len=strlen($q);
    foreach($a as $name)
    { if (stristr($q, substr($name,0,$len)))
      { if ($hint==="")
        { $hint = "  Suggestions: "; $hint.=$name; }
        else
        { $hint .= ", $name"; }
      }
    }
  }

// Output "no suggestion" if no hint were found
// or output the correct values
echo $hint==="" ? "  No suggestions" : $hint;
?> 