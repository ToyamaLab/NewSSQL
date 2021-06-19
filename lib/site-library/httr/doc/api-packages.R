<<<<<<< HEAD
## ----setup, include = FALSE----------------------------------------------
library(httr)
knitr::opts_chunk$set(comment = "#>", collapse = TRUE)

## ------------------------------------------------------------------------
=======
## ----setup, include = FALSE---------------------------------------------------
library(httr)
knitr::opts_chunk$set(comment = "#>", collapse = TRUE)

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
library(httr)
github_api <- function(path) {
  url <- modify_url("https://api.github.com", path = path)
  GET(url)
}

resp <- github_api("/repos/hadley/httr")
resp

<<<<<<< HEAD
## ------------------------------------------------------------------------
GET("http://www.colourlovers.com/api/color/6B4106?format=xml")
GET("http://www.colourlovers.com/api/color/6B4106?format=json")

## ------------------------------------------------------------------------
http_type(resp)

## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
GET("http://www.colourlovers.com/api/color/6B4106?format=xml")
GET("http://www.colourlovers.com/api/color/6B4106?format=json")

## -----------------------------------------------------------------------------
http_type(resp)

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
github_api <- function(path) {
  url <- modify_url("https://api.github.com", path = path)
  
  resp <- GET(url)
  if (http_type(resp) != "application/json") {
    stop("API did not return json", call. = FALSE)
  }
  
  resp
}

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
github_api <- function(path) {
  url <- modify_url("https://api.github.com", path = path)
  
  resp <- GET(url)
  if (http_type(resp) != "application/json") {
    stop("API did not return json", call. = FALSE)
  }
  
  jsonlite::fromJSON(content(resp, "text"), simplifyVector = FALSE)
}

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
github_api <- function(path) {
  url <- modify_url("https://api.github.com", path = path)
  
  resp <- GET(url)
  if (http_type(resp) != "application/json") {
    stop("API did not return json", call. = FALSE)
  }
  
  parsed <- jsonlite::fromJSON(content(resp, "text"), simplifyVector = FALSE)
  
  structure(
    list(
      content = parsed,
      path = path,
      response = resp
    ),
    class = "github_api"
  )
}

print.github_api <- function(x, ...) {
  cat("<GitHub ", x$path, ">\n", sep = "")
  str(x$content)
  invisible(x)
}

github_api("/users/hadley")

<<<<<<< HEAD
## ---- error = TRUE-------------------------------------------------------
=======
## ---- error = TRUE------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
github_api <- function(path) {
  url <- modify_url("https://api.github.com", path = path)
  
  resp <- GET(url)
  if (http_type(resp) != "application/json") {
    stop("API did not return json", call. = FALSE)
  }
  
  parsed <- jsonlite::fromJSON(content(resp, "text"), simplifyVector = FALSE)
  
  if (http_error(resp)) {
    stop(
      sprintf(
        "GitHub API request failed [%s]\n%s\n<%s>", 
        status_code(resp),
        parsed$message,
        parsed$documentation_url
      ),
      call. = FALSE
    )
  }
  
  structure(
    list(
      content = parsed,
      path = path,
      response = resp
    ),
    class = "github_api"
  )
}
github_api("/user/hadley")

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
ua <- user_agent("http://github.com/hadley/httr")
ua

github_api <- function(path) {
  url <- modify_url("https://api.github.com", path = path)
  
  resp <- GET(url, ua)
  if (http_type(resp) != "application/json") {
    stop("API did not return json", call. = FALSE)
  }
  
  parsed <- jsonlite::fromJSON(content(resp, "text"), simplifyVector = FALSE)
  
  if (status_code(resp) != 200) {
    stop(
      sprintf(
        "GitHub API request failed [%s]\n%s\n<%s>", 
        status_code(resp),
        parsed$message,
        parsed$documentation_url
      ),
      call. = FALSE
    )
  }
  
  structure(
    list(
      content = parsed,
      path = path,
      response = resp
    ),
    class = "github_api"
  )
}

<<<<<<< HEAD
## ---- eval = FALSE-------------------------------------------------------
=======
## ---- eval = FALSE------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#  # modify_url
#  POST(modify_url("https://httpbin.org", path = "/post"))
#  
#  # query arguments
#  POST("http://httpbin.org/post", query = list(foo = "bar"))
#  
#  # headers
#  POST("http://httpbin.org/post", add_headers(foo = "bar"))
#  
#  # body
#  ## as form
#  POST("http://httpbin.org/post", body = list(foo = "bar"), encode = "form")
#  ## as json
#  POST("http://httpbin.org/post", body = list(foo = "bar"), encode = "json")

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
f <- function(x = c("apple", "banana", "orange")) {
  match.arg(x)
}
f("a")

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
github_pat <- function() {
  pat <- Sys.getenv('GITHUB_PAT')
  if (identical(pat, "")) {
    stop("Please set env var GITHUB_PAT to your github personal access token",
      call. = FALSE)
  }

  pat
}

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
rate_limit <- function() {
  github_api("/rate_limit")
}
rate_limit()

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
rate_limit <- function() {
  req <- github_api("/rate_limit")
  core <- req$content$resources$core

  reset <- as.POSIXct(core$reset, origin = "1970-01-01")
  cat(core$remaining, " / ", core$limit,
    " (Resets at ", strftime(reset, "%H:%M:%S"), ")\n", sep = "")
}

rate_limit()

