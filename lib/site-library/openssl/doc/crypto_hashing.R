<<<<<<< HEAD
## ---- echo = FALSE, message = FALSE--------------------------------------
knitr::opts_chunk$set(comment = "")
library(openssl)

## ------------------------------------------------------------------------
md5("foo")
md5(charToRaw("foo"))

## ------------------------------------------------------------------------
# Vectorized for strings
md5(c("foo", "bar", "baz"))

## ------------------------------------------------------------------------
=======
## ---- echo = FALSE, message = FALSE-------------------------------------------
knitr::opts_chunk$set(comment = "")
library(openssl)

## -----------------------------------------------------------------------------
md5("foo")
md5(charToRaw("foo"))

## -----------------------------------------------------------------------------
# Vectorized for strings
md5(c("foo", "bar", "baz"))

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
# Stream-hash a file
myfile <- system.file("CITATION")
md5(file(myfile))

<<<<<<< HEAD
## ----eval=FALSE----------------------------------------------------------
#  # Stream-hash from a network connection
#  md5(url("http://cran.us.r-project.org/bin/windows/base/old/3.1.1/R-3.1.1-win.exe"))

## ------------------------------------------------------------------------
=======
## ----eval=FALSE---------------------------------------------------------------
#  # Stream-hash from a network connection
#  md5(url("http://cran.us.r-project.org/bin/windows/base/old/3.1.1/R-3.1.1-win.exe"))

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
# Compare to digest
library(digest)
digest("foo", "md5", serialize = FALSE)

# Other way around
digest(cars, skip = 0)
md5(serialize(cars, NULL))

