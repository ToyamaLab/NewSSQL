<<<<<<< HEAD
## ---- echo = FALSE-------------------------------------------------------
library(DBI)
knitr::opts_chunk$set(collapse = TRUE, comment = "#>")

## ------------------------------------------------------------------------
=======
## ---- echo = FALSE------------------------------------------------------------
library(DBI)
knitr::opts_chunk$set(collapse = TRUE, comment = "#>")

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#' Driver for Kazam database.
#' 
#' @keywords internal
#' @export
#' @import DBI
#' @import methods
setClass("KazamDriver", contains = "DBIDriver")

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#' @export
#' @rdname Kazam-class
setMethod("dbUnloadDriver", "KazamDriver", function(drv, ...) {
  TRUE
})

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
setMethod("show", "KazamDriver", function(object) {
  cat("<KazamDriver>\n")
})

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#' @export
Kazam <- function() {
  new("KazamDriver")
}

Kazam()

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#' Kazam connection class.
#' 
#' @export
#' @keywords internal
setClass("KazamConnection", 
  contains = "DBIConnection", 
  slots = list(
    host = "character", 
    username = "character", 
    # and so on
    ptr = "externalptr"
  )
)

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#' @param drv An object created by \code{Kazam()} 
#' @rdname Kazam
#' @export
#' @examples
#' \dontrun{
#' db <- dbConnect(RKazam::Kazam())
#' dbWriteTable(db, "mtcars", mtcars)
#' dbGetQuery(db, "SELECT * FROM mtcars WHERE cyl == 4")
#' }
setMethod("dbConnect", "KazamDriver", function(drv, ...) {
  # ...
  
  new("KazamConnection", host = host, ...)
})

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#' Kazam results class.
#' 
#' @keywords internal
#' @export
setClass("KazamResult", 
  contains = "DBIResult",
  slots = list(ptr = "externalptr")
)

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#' Send a query to Kazam.
#' 
#' @export
#' @examples 
#' # This is another good place to put examples
setMethod("dbSendQuery", "KazamConnection", function(conn, statement, ...) {
  # some code
  new("KazamResult", ...)
})

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#' @export
setMethod("dbClearResult", "KazamResult", function(res, ...) {
  # free resources
  TRUE
})

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#' Retrieve records from Kazam query
#' @export
setMethod("dbFetch", "KazamResult", function(res, n = -1, ...) {
  ...
})

# (optionally)

#' Find the database data type associated with an R object
#' @export
setMethod("dbDataType", "KazamConnection", function(dbObj, obj, ...) {
  ...
})

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#' @export
setMethod("dbHasCompleted", "KazamResult", function(res, ...) { 
  
})

