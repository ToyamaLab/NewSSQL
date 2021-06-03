<<<<<<< HEAD
## ---- echo = FALSE-------------------------------------------------------
library(httr)
knitr::opts_chunk$set(comment = "#>", collapse = TRUE)

## ------------------------------------------------------------------------
library(httr)
r <- GET("http://httpbin.org/get")

## ------------------------------------------------------------------------
r

## ------------------------------------------------------------------------
=======
## ---- echo = FALSE------------------------------------------------------------
library(httr)
knitr::opts_chunk$set(comment = "#>", collapse = TRUE)

## -----------------------------------------------------------------------------
library(httr)
r <- GET("http://httpbin.org/get")

## -----------------------------------------------------------------------------
r

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
status_code(r)
headers(r)
str(content(r))

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
r <- GET("http://httpbin.org/get")
# Get an informative description:
http_status(r)

# Or just access the raw code:
r$status_code

<<<<<<< HEAD
## ------------------------------------------------------------------------
warn_for_status(r)
stop_for_status(r)

## ------------------------------------------------------------------------
r <- GET("http://httpbin.org/get")
content(r, "text")

## ---- eval = FALSE-------------------------------------------------------
#  content(r, "text", encoding = "ISO-8859-1")

## ------------------------------------------------------------------------
content(r, "raw")

## ---- eval = FALSE-------------------------------------------------------
#  bin <- content(r, "raw")
#  writeBin(bin, "myfile.txt")

## ------------------------------------------------------------------------
# JSON automatically parsed into named list
str(content(r, "parsed"))

## ------------------------------------------------------------------------
headers(r)

## ------------------------------------------------------------------------
headers(r)$date
headers(r)$DATE

## ------------------------------------------------------------------------
r <- GET("http://httpbin.org/cookies/set", query = list(a = 1))
cookies(r)

## ------------------------------------------------------------------------
r <- GET("http://httpbin.org/cookies/set", query = list(b = 1))
cookies(r)

## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
warn_for_status(r)
stop_for_status(r)

## -----------------------------------------------------------------------------
r <- GET("http://httpbin.org/get")
content(r, "text")

## ---- eval = FALSE------------------------------------------------------------
#  content(r, "text", encoding = "ISO-8859-1")

## -----------------------------------------------------------------------------
content(r, "raw")

## ---- eval = FALSE------------------------------------------------------------
#  bin <- content(r, "raw")
#  writeBin(bin, "myfile.txt")

## -----------------------------------------------------------------------------
# JSON automatically parsed into named list
str(content(r, "parsed"))

## -----------------------------------------------------------------------------
headers(r)

## -----------------------------------------------------------------------------
headers(r)$date
headers(r)$DATE

## -----------------------------------------------------------------------------
r <- GET("http://httpbin.org/cookies/set", query = list(a = 1))
cookies(r)

## -----------------------------------------------------------------------------
r <- GET("http://httpbin.org/cookies/set", query = list(b = 1))
cookies(r)

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
r <- GET("http://httpbin.org/get", 
  query = list(key1 = "value1", key2 = "value2")
)
content(r)$args

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
r <- GET("http://httpbin.org/get", 
  query = list(key1 = "value 1", "key 2" = "value2", key2 = NULL))
content(r)$args

<<<<<<< HEAD
## ------------------------------------------------------------------------
r <- GET("http://httpbin.org/get", add_headers(Name = "Hadley"))
str(content(r)$headers)

## ------------------------------------------------------------------------
r <- GET("http://httpbin.org/cookies", set_cookies("MeWant" = "cookies"))
content(r)$cookies

## ------------------------------------------------------------------------
r <- POST("http://httpbin.org/post", body = list(a = 1, b = 2, c = 3))

## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
r <- GET("http://httpbin.org/get", add_headers(Name = "Hadley"))
str(content(r)$headers)

## -----------------------------------------------------------------------------
r <- GET("http://httpbin.org/cookies", set_cookies("MeWant" = "cookies"))
content(r)$cookies

## -----------------------------------------------------------------------------
r <- POST("http://httpbin.org/post", body = list(a = 1, b = 2, c = 3))

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
url <- "http://httpbin.org/post"
body <- list(a = 1, b = 2, c = 3)

# Form encoded
r <- POST(url, body = body, encode = "form")
# Multipart encoded
r <- POST(url, body = body, encode = "multipart")
# JSON encoded
r <- POST(url, body = body, encode = "json")

<<<<<<< HEAD
## ---- eval = FALSE-------------------------------------------------------
=======
## ---- eval = FALSE------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#  POST(url, body = body, encode = "multipart", verbose()) # the default
#  POST(url, body = body, encode = "form", verbose())
#  POST(url, body = body, encode = "json", verbose())

<<<<<<< HEAD
## ---- eval = FALSE-------------------------------------------------------
#  POST(url, body = upload_file("mypath.txt"))
#  POST(url, body = list(x = upload_file("mypath.txt")))

## ------------------------------------------------------------------------
=======
## ---- eval = FALSE------------------------------------------------------------
#  POST(url, body = upload_file("mypath.txt"))
#  POST(url, body = list(x = upload_file("mypath.txt")))

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
sessionInfo()

