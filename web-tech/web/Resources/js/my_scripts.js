var xmlhttp;
var isIE;
var user;
var megusta="";
var nomegusta="";
var quiza="";
var invalido = "<div id='mialerta' class='alert alert-warning alert-danger'><button type='button'" +
                "class='close' data-dismiss='alert' aria-hidden='true'>&times;</button><strong>Warning!</strong>"+
                        " That ingredient does not exist in DBPedia. Please, try again."+"</div>";
var products ="";

function initRequest() {
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function assignUser() {
    if (xmlhttp.readyState===4) {
        if(xmlhttp.status===200) //GET returning a response
          {
          user=xmlhttp.responseXML.getElementsByTagName("ID")[0].firstChild.nodeValue;
          }
    }
}

function getUser(url) {   
    initRequest();
    xmlhttp.onreadystatechange=assignUser;
    xmlhttp.open("GET",url,true);
    xmlhttp.send(null);
}

function addIngredient() {
    var x,ing;
    
    if (xmlhttp.readyState===4) {
        if(xmlhttp.status===200) {//PUT returning a response
          x=xmlhttp.responseXML.getElementsByTagName("INGREDIENT")[0];
          ing=x.getElementsByTagName("PRODUCT")[0].firstChild.nodeValue;
          if(ing === "invalid") {
              document.getElementById("InvalidIngredient").innerHTML = invalido;
          }
          else {
              alert("valido");
                var li=formatLi(ing);
                if(document.getElementById('Choices_0').checked) {
                    //Like radio button is checked
                    megusta = megusta.concat(li);
                    document.getElementById("meGustaUL").innerHTML=megusta;
                    products = products.concat(formatIngredientRated(formatProduct(ing),1));
                }
                else {
                    if(document.getElementById('Choices_1').checked) {
                        //Maybe radio button is checked
                        quiza = quiza.concat(li);
                        document.getElementById("puedeUL").innerHTML=quiza;
                        products = products.concat(formatIngredientRated(formatProduct(ing),0));
                    }
                    else {
                        if(document.getElementById('Choices_2').checked) {
                            //Dislike radio button is checked
                            nomegusta = nomegusta.concat(li);
                            document.getElementById("noMeGustaUL").innerHTML=nomegusta;
                            products = products.concat(formatIngredientRated(formatProduct(ing),-1));
                        }
                    }//else
                }//else
          }//else valid ingredient
        }//if status
    }//if ready
}//function

function putIngredient(url,ing) { 
    var xml = formatUser(formatIngredient(formatProduct(ing)));
    initRequest();
    xmlhttp.onreadystatechange=addIngredient;
    xmlhttp.open("PUT",url,true);
    xmlhttp.setRequestHeader("Content-type","application/xml");
    xmlhttp.send(xml);
}

function resultados(recipes,file,div) {
    var att,img,ing,sni,xml,html,ingsaux;
    xml='<ul class="media-list">';
    html="";
    for(var i=0;i<recipes.length;i++) {
        att=recipes[i].getAttribute("NAME");
        img=recipes[i].getElementsByTagName("IMG")[0].firstChild.nodeValue;
        sni=x[i].getElementsByTagName("SNIPPET")[0].firstChild.nodeValue;
        ing=recipes[i].getElementsByTagName("INGREDIENT").getElementsByTagName("PRODUCT");
        ingsaux="";
        for(var j=0;j<ing.length;j++) {
            ings=ings+' '+ing[j].firstChild.nodeValue;
            ingsaux=ingsaux+'<li>'+ing+'</li>';
        }//for
        xml=xml+'<li class="media"><a class="pull-left" href="'+img+'">'+
                '<img class="media-object" src="Resources/icons/64x64/images.jpg">'+
                        '</a><div class="media-body"><h4 class="media-heading">'+
                        '<a class="highslide" onclick="return hs.htmlExpand(this, { contentId:'+"'highslide-html-ajax', wrapperClassName: 'highslide-white',"+ 
                                    "outlineType: 'rounded-white', outlineWhileAnimating: true, objectType: 'ajax', preserveContent: true } )"+'"'+ 
                                        'href="'+file+'#receta'+i+'">'+att+'</a></h4><h5>'+ings+'</h5></div></li>'+
                        '<img src="Resources/icons/36x36/like.png" onclick="likeRecipe()"/>I Like it!';
        html=html+'<div id="receta'+i+'"><div><div class="highslide-gallery">'+
                  '<div id="imgReceta"><a href='+img+' class="highslide" onclick="return hs.expand(this)"></div>'+
                  '<img src="Resources/icons/64x64/imageLoadRecipe.png" alt="Highslide JS" title="Click to enlarge" />'+
                  '<div class="highslide-caption"></div></div>'+
                  '<h2><div id="titleReceta">'+att+'</div></h2><p>'+
                  '<div id="snippetReceta">'+sni+'</div></p>'+
                  '<div><h3>Ingredients</h3><ul><div id="ingredientsReceta">'+ingsaux+'</div></ul></div>'+
                  '<img src="Resources/icons/36x36/like.png" onclick="likeRecipe()"/>I Like it!';
   }//for
   xml=xml+'</ul>';
   document.getElementById(div).innerHTML=xml;
   document.getElementById("resultadosRecetas").innerHTML=html;
}

function showRecipes(file,div) {
    if (xmlhttp.readyState===4) {
        if(xmlhttp.status===200) //GET returning a response
          {
          resultados(xmlhttp.responseXML.getElementsByTagName("RECIPE"),file,div);
         }//if status
    }//if state
}//function


function getRelated() {
    initRequest();
    xmlhttp.onreadystatechange=showRecipes("related.html","misrelacionadas");
    xmlhttp.open("GET","http://localhost:8080/web-tech/webresources/lookandcook/related/"+user,true);
    xmlhttp.send();
}

function getRecommended() {
    initRequest();
    xmlhttp.onreadystatechange=showRecipes("recommended.html","misrecomendaciones");
    xmlhttp.open("GET","http://localhost:8080/web-tech/webresources/lookandcook/recommendations/"+user,true);
    xmlhttp.send();
}

function getRecipe() {
    initRequest();
    xmlhttp.onreadystatechange=showRecipes("recetas.html","misrecetas");
    xmlhttp.open("GET",'http://localhost:8080/web-tech/webresources/lookandcook/recipe/'+user,true);
    xmlhttp.send();
}

function putRecipe() {
    alert(xmlhttp.readyState);
    if (xmlhttp.readyState===4) {
        alert("ready");
        if(xmlhttp.status===200) //PUT returning a response
          {
              alert("envio get recipe");
          getRecipe();
          getRecommended();
          getRelated();
          }
    }
}

function searchRecipes() { 
    var xml = formatUser(products);
    initRequest();
    xmlhttp.onreadystatechange=putRecipe();
    xmlhttp.open("PUT",'http://localhost:8080/web-tech/webresources/lookandcook/recipe/'+user,true);
    xmlhttp.setRequestHeader("Content-type","application/xml");
    xmlhttp.send(xml);
}

function formatIngredient(ings) {
    var xml="<INGREDIENT>"+ings+"</INGREDIENT>";
    return xml;
}

function formatIngredientRated(ing,rate) {
    var xml='<INGREDIENT PRIORITY="'+rate+'">'+ing+"</INGREDIENT>";
    return xml;
}

function formatProduct(ing) {
    var xml="<PRODUCT>"+ing+"</PRODUCT>";
    return xml;
}

function formatLi(ing) {
    var xml="<li id="+ing+">"+ing+"</li>";
    return xml;
}

function formatUser(something) {
    var xml="<USER>"+something+"</USER>";
    return xml;
}

function likeRecipe(url) {
    initRequest();
    xmlhttp.open("PUT",url,true);
    xmlhttp.setRequestHeader("Content-type","application/xml");
    xmlhttp.send(xml);
}

function topRecipes(url) {
    initRequest();
    xmlhttp.onreadystatechange=showTop();
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}

var ingredients = ["almonds",
"apple",
"apricot",
"asparagus",
"avocado",
"banana",
"barley",
"basil",
"beef",
"beet greens",
"beets",
"bell peppers",
"black beans",
"black pepper",
"blueberries",
"bok choy",
"broccoli",
"brown rice",
"brussels sprouts",
"buckwheat",
"cabbage",
"cantaloupe",
"carrots",
"cashews",
"cauliflower",
"cayenne pepper",
"celery",
"cheese",
"chicken",
"chili pepper",
"cilantro",
"cinnamon",
"cloves",
"cod",
"collard greens",
"coriander",
"corn",
"cranberries",
"cucumber",
"cumin",
"dill",
"dried peas",
"eggplant",
"eggs",
"fennel",
"figs",
"flax seeds",
"garbanzo beans",
"garlic",
"ginger",
"grapefruit",
"grapes",
"green beans",
"green peas",
"green tea",
"honey",
"kale",
"kidney beans",
"kiwifruit",
"lamb",
"leeks",
"lemons", 
"limes",
"lentils",
"lima beans",
"limes",
"maple syrup",
"milk",
"millet",
"miso",
"molasses",
"blackstrap",
"mushrooms",
"crimini",
"mushrooms",
"shiitake",
"mustard greens",
"mustard seeds",
"navy beans",
"oats",
"olive oil",
"olives",
"onions",
"oranges",
"oregano",
"papaya",
"parsley",
"peanuts",
"pear",
"peppermint",
"pineapple",
"pinto beans",
"plum",
"potatoes",
"prunes",
"pumpkin seeds",
"quinoa",
"raisins",
"raspberries",
"romaine lettuce",
"rosemary",
"rye",
"sage",
"salmon",
"sardines",
"scallops",
"sea vegetables",
"sesame seeds",
"shrimp",
"soy sauce",
"soybeans",
"spelt",
"spinach",
"strawberries",
"summer squash",
"sunflower seeds",
"sweet potato",
"swiss chard",
"tempeh",
"thyme",
"tofu",
"tomatoes",
"tuna",
"turkey",
"turmeric",
"turnip greens",
"walnuts",
"water",
"watermelon",
"wheat",
"winter squash",
"yams",
"yogurt",
"udo",
"udon noodles",
"ugli fruit",
"umeboshi",
"unsweetened chocolate",
"urad dal",
"ice cream",
"ice wine",
"icing sugar",
"indienne",
"coffee",
"irish cream liqueur",
"irish mist",
"isinglass",
"jack cheese",
"jaggery",
"jambalaya",
"jamon serrano",
"jamun",
"jelly beans",
"jelly roll",
"jelly tots",
"Jerusalem artichokes",
"jicama",
"joint",
"jowl",
"za'atar",
"zabaglione",
"zest",
"zinfandel wine",
"vanilla",
"vanilla bean",
"veal",
"vegemite",
"vegetable marrow",
"veloute",
"velveeta",
"venison",
"vermouth",
"vidalia onions",
"vincotto",
"vinegar",
"vital wheat gluten",
"nasturtium",
"navy beans",
"nectarines",
"nigella seeds",
"nopales",
"nori"
];

function getHint(input) {
    // get the q parameter from URL
    var hint="";
    var ingredient;
    
    // lookup all hints from array if input is different from ""
    if (input !== "") {
        input.toLowerCase(); 
        for(var i=0;i<ingredients.length;i++) {
            ingredient = ingredients[i];
            if (ingredient.indexOf(input) === 0 ) { 
              
                if (hint === ""){
                    hint = "  Suggestions: ";
                    hint =hint + ingredient;
                }
                else {
                    hint = hint + ", " + ingredient;
                }
            }
          }
        }

    // Output "no suggestion" if no hint were found
    // or output the correct values
    if(hint === "" && input!=="") { //NUEVO
        hint = "  No suggestions";
    }
    document.getElementById("txtHint").innerHTML=hint;
}

