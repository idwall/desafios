var request = require('request')
var rp = require('request-promisse')
var cheerio = require('cheerio')
var url = require('url-parse')
var colors = require('colors')
var prompt = require('prompt')

// Validação para o prompt de comando
var schema = {
    properties: {
        subreddits: {
            pattern: /^[a-zA-Z0-9_.-;]*$/,
            message: "Subreddits podem ser apenas letras, números, underscore, ponto, hífen ou ponto e vírgula",
            required: true
        }
    }
}

prompt.message = "Escreva subreddits divididos por ponto e vírgula: ".blue
prompt.delimiter = ""
prompt.start()

// Website para rastrear, list de subreddit e threads json
var crawlerWebsite = "http://www.reddit.com/"
var threadsJson = []
console.log("Website para rastrear: ".green + crawlerWebsite)

// Recebe valor pela linha de comando
/*
prompt.get(schema, function (err, result) {
    subredditList = result.subreddits.split(";")
    console.log("Subreddits para consulta: ".green + subredditList)
    console.log("Subreddits total: ".green + subredditList)
})
*/
threadsJson.push({cats: []});
crawlerUrl(crawlerWebsite + "r/cats")

function crawlerUrl(requestWebsite, threadsJson){
    // Requisição http para o website
    request(requestWebsite, function(err, res, body){
        if(!err){
            // Caso resposta do servidor ok
            if(res.statusCode === 200) {

                // Parse do elemento body pelo DOM usando jquery
                var $ = cheerio.load(body)
                // console.log("Título do website: ".green + $('title').text())
                console.log(crawlerThreads($, threadsJson))
            }
        }else{
            // Reporte de erro pelo servidor
            console.log("Erro encontrado: ".red + err)
        }
    })
}

function crawlerThreads($, elementsList){
    elementsList.push({ 
        upVotes: crawlerElements($, "div.score.unvoted"),
        tituloDaThread: crawlerElements($, "a.title.may-blank"),
        linkDaThread: "http://www.reddit,com/asdadasdad",
        linkDeComentarios: "http://www.reddit,com/asdadasdad"
    })
}

/*
function crawlerThreads($, elementsList = [] || elementsList){
    elementsList = crawlerElements($, "div.score.unvoted")
    return elementsList.filter(checkThreads)
}

function checkThreads(elementsList){
    return elementsList >= 5000;
}

function crawlerElements($, element, elementsList = [] || elementsList){
    $(element).each(function(index) {
        elementsList[index] = parseInt($(this).attr("title"))
    });
    return elementsList
}
*/