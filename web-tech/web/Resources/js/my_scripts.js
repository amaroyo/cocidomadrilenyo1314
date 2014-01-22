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

function addIngredient(entrada) {
    var x,xx;
    
    
          if(entrada === "invalid") {
              document.getElementById("InvalidIngredient").innerHTML = invalido;
          }
          else {
                var li=formatLi(entrada);
                if(document.getElementById('Choices_0').checked) {
                    //Like radio button is checked
                    megusta = megusta.concat(li);
                    document.getElementById("meGustaUL").innerHTML=megusta;
                    products = products.concat(formatIngredientRated(formatProduct(entrada),1));
                }
                else {
                    if(document.getElementById('Choices_1').checked) {
                        //Maybe radio button is checked
                        quiza = quiza.concat(li);
                        document.getElementById("puedeUL").innerHTML=quiza;
                        products = products.concat(formatIngredientRated(formatProduct(entrada),0));
                    }
                    else {
                        if(document.getElementById('Choices_2').checked) {
                            //Dislike radio button is checked
                            nomegusta = nomegusta.concat(li);
                            document.getElementById("noMeGustaUL").innerHTML=nomegusta;
                            products = products.concat(formatIngredientRated(formatProduct(entrada),-1));
                        }
                    }//else
                }//else
          }//else valid ingredient
       
   
}//function

function putIngredient(url,ing) { 
    var xml = formatUser(formatIngredient(formatProduct(ing)));
    initRequest();
    xmlhttp.onreadystatechange=addIngredient;
    xmlhttp.open("PUT",url,true);
    xmlhttp.setRequestHeader("Content-type","application/xml");
    xmlhttp.send(xml);
}


function bla() {
    if (xmlhttp.readyState===4) {
        if(xmlhttp.status===200) //GET returning a response
          {
          getRecipe(url);
          }
    }
}

function bla2() {
    var att,img,ing,sni,xml;
    if (xmlhttp.readyState===4) {
        if(xmlhttp.status===200) //GET returning a response
          {
          x=xmlhttp.responseXML.getElementsByTagName("RECIPE");
          xml='<ul class="media-list">';
          for(var i=0;i<x.length;i++) {
               att=x[i].getAttribute("NAME");
              //img=x[i].getElementsByTagName("IMG")[0].firstChild.nodeValue;
              ing=x[i].getElementsByTagName("INGREDIENT");
              //sni=x[i].getElementsByTagName("SNIPPET")[0].firstChild.nodeValue;
              xml=xml+'<li class="media">'+'<a class="pull-left" href="#">'+
                      '<img class="media-object" src="Resources/icons/64x64/images.jpg">'+
                        '</a><div class="media-body"><h4 class="media-heading">'+att+'</h4><h5>'+ing+'</h5></div></li>';
             
         }//for
         xml=xml+'</ul>';
         document.getElementById("misrecetas").innerHTML=xml;
         }//if status
    }//if state
}//function

function getRecipe(url) {
    initRequest();
    xmlhttp.onreadystatechange=bla2;
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}

function searchRecipes(url) { 
    var xml = formatUser(products);
    initRequest();
    xmlhttp.onreadystatechange=bla;
    xmlhttp.open("PUT",url,true);
    xmlhttp.setRequestHeader("Content-type","application/xml");
    xmlhttp.send(xml);
}

function formatIngredient(ings) {
    var xml="<INGREDIENT>"+ings+"</INGREDIENT>";
    return xml;
}

function formatIngredientRated(ing,rate) {
    var xml="<INGREDIENT PRIORITY="+rate+">"+ing+"</INGREDIENT>";
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
"nori",
"meat",
"curry",
"rice"
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

function loadXMLFile(url) {
    initRequest();
    xmlhttp.onreadystatechange=showXMLFile;
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}

function showXMLFile(url) {
    var att,img,ing,sni,xml;
    var incs = "";
    var red = '<a href="recetilla.html"';
    var red2 = 'onclick="return hs.htmlExpand(this, { objectType:';
    var red3 = "'ajax'}";
    var red4 = ')">';
                               
    
    if (xmlhttp.readyState===4) {
        if(xmlhttp.status===200) //GET returning a response
          {
          x=xmlhttp.responseXML.getElementsByTagName("RECIPE");
          xml='<ul class="media-list">';
          for(var i=0;i<x.length;i++) {
               att=x[i].getAttribute("NAME");
              img=x[i].getElementsByTagName("IMG")[0].firstChild.nodeValue;
              ing=x[i].getElementsByTagName("INGREDIENT")[0].getElementsByTagName("PRODUCT");
              for(var j=0;j<ing.length;j++) {
                  incs = incs + " " + ing[j].firstChild.nodeValue;
              }
              //sni=x[i].getElementsByTagName("SNIPPET")[0].firstChild.nodeValue;
              xml=xml+'<li class="media">'+'<a class="pull-left" href="'+img+'">'+
                      '<img class="media-object" src="' + img + '">'+
                        '</a><div class="media-body"><h4 class="media-heading">'
                        +red+red2+red3+red4
                        +att+' </a></h4><h5>'+incs+'</h5></div></li>';
             
         }//for
         xml=xml+'</ul>';
         document.getElementById("misrecetas").innerHTML=xml;
         
         }//if status
    }//if state
}//function

