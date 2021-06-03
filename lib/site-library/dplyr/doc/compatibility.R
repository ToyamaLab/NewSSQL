<<<<<<< HEAD
## ----setup, include = FALSE----------------------------------------------
library(dplyr)
knitr::opts_chunk$set(collapse = T, comment = "#>")

## ---- results = "hide"---------------------------------------------------
=======
## ----setup, include = FALSE---------------------------------------------------
library(dplyr)
knitr::opts_chunk$set(collapse = T, comment = "#>")

## ---- results = "hide"--------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
if (utils::packageVersion("dplyr") > "0.5.0") {
  # code for new version
} else {
  # code for old version
}

<<<<<<< HEAD
## ---- eval = FALSE-------------------------------------------------------
=======
## ---- eval = FALSE------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#  if (utils::packageVersion("dplyr") > "0.5.0") {
#    dbplyr::build_sql(...)
#  } else {
#    dplyr::build_sql(...)
#  }

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#' @rawNamespace
#' if (utils::packageVersion("dplyr") > "0.5.0") {
#'   importFrom("dbplyr", "build_sql")
#' } else {
#'   importFrom("dplyr", "build_sql")
#' }

<<<<<<< HEAD
## ---- eval = FALSE-------------------------------------------------------
=======
## ---- eval = FALSE------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#  wrap_dbplyr_obj("build_sql")
#  
#  wrap_dbplyr_obj("base_agg")

<<<<<<< HEAD
## ---- eval = FALSE-------------------------------------------------------
#  quo <- quo(cyl)
#  select(mtcars, !! quo)

## ---- results = "hide"---------------------------------------------------
=======
## ---- eval = FALSE------------------------------------------------------------
#  quo <- quo(cyl)
#  select(mtcars, !! quo)

## ---- results = "hide"--------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
sym <- quote(cyl)
select(mtcars, !! sym)

call <- quote(mean(cyl))
<<<<<<< HEAD
summarise(mtcars, !! call)

## ------------------------------------------------------------------------
=======
summarise(mtcars, cyl = !! call)

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
quo(!! sym)
quo(!! call)

rlang::as_quosure(sym)
rlang::as_quosure(call)

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
f <- ~cyl
f
rlang::as_quosure(f)

<<<<<<< HEAD
## ------------------------------------------------------------------------
rlang::sym("cyl")
rlang::syms(letters[1:3])

## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
rlang::sym("cyl")
rlang::syms(letters[1:3])

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
syms <- rlang::syms(c("foo", "bar", "baz"))
quo(my_call(!!! syms))

fun <- rlang::sym("my_call")
quo((!!fun)(!!! syms))

<<<<<<< HEAD
## ------------------------------------------------------------------------
call <- rlang::lang("my_call", !!! syms)
=======
## -----------------------------------------------------------------------------
call <- rlang::call2("my_call", !!! syms)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
call

rlang::as_quosure(call)

# Or equivalently:
<<<<<<< HEAD
quo(!! rlang::lang("my_call", !!! syms))

## ---- eval=FALSE---------------------------------------------------------
#  lazyeval::interp(~ mean(var), var = rlang::sym("mpg"))

## ---- eval=FALSE---------------------------------------------------------
#  var <- "mpg"
#  quo(mean(!! rlang::sym(var)))

## ---- eval = FALSE-------------------------------------------------------
=======
quo(!! rlang::call2("my_call", !!! syms))

## ---- eval=FALSE--------------------------------------------------------------
#  lazyeval::interp(~ mean(var), var = rlang::sym("mpg"))

## ---- eval=FALSE--------------------------------------------------------------
#  var <- "mpg"
#  quo(mean(!! rlang::sym(var)))

## ---- eval = FALSE------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#  filter_.tbl_df <- function(.data, ..., .dots = list()) {
#    dots <- compat_lazy_dots(.dots, caller_env(), ...)
#    filter(.data, !!! dots)
#  }

<<<<<<< HEAD
## ---- eval = FALSE-------------------------------------------------------
=======
## ---- eval = FALSE------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#  filter.default <- function(.data, ...) {
#    filter_(.data, .dots = compat_as_lazy_dots(...))
#  }

<<<<<<< HEAD
## ---- eval = FALSE-------------------------------------------------------
=======
## ---- eval = FALSE------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#  filter.sf <- function(.data, ...) {
#    st_as_sf(NextMethod())
#  }

<<<<<<< HEAD
## ---- eval = FALSE-------------------------------------------------------
#  mutate_each(starwars, funs(as.character))
#  mutate_all(starwars, funs(as.character))

## ---- eval = FALSE-------------------------------------------------------
#  mutate_all(starwars, as.character)

## ---- eval = FALSE-------------------------------------------------------
#  mutate_each(starwars, funs(as.character), height, mass)
#  mutate_at(starwars, vars(height, mass), as.character)

## ---- eval = FALSE-------------------------------------------------------
#  summarise_at(mtcars, vars(starts_with("d")), mean)

## ---- eval = FALSE-------------------------------------------------------
=======
## ---- eval = FALSE------------------------------------------------------------
#  mutate_each(starwars, funs(as.character))
#  mutate_all(starwars, funs(as.character))

## ---- eval = FALSE------------------------------------------------------------
#  mutate_all(starwars, as.character)

## ---- eval = FALSE------------------------------------------------------------
#  mutate_each(starwars, funs(as.character), height, mass)
#  mutate_at(starwars, vars(height, mass), as.character)

## ---- eval = FALSE------------------------------------------------------------
#  summarise_at(mtcars, vars(starts_with("d")), mean)

## ---- eval = FALSE------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#  mutate_at(starwars, c("height", "mass"), as.character)

