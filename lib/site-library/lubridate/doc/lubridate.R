<<<<<<< HEAD
## ---- include = FALSE----------------------------------------------------
knitr::opts_chunk$set(comment = "#>", collapse = TRUE)

## ------------------------------------------------------------------------
=======
## ---- include = FALSE---------------------------------------------------------
knitr::opts_chunk$set(comment = "#>", collapse = TRUE)

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
library(lubridate)
ymd("20110604")
mdy("06-04-2011")
dmy("04/06/2011")

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
arrive <- ymd_hms("2011-06-04 12:00:00", tz = "Pacific/Auckland")
arrive
leave <- ymd_hms("2011-08-10 14:00:00", tz = "Pacific/Auckland")
leave

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
second(arrive)
second(arrive) <- 25
arrive
second(arrive) <- 0

wday(arrive)
wday(arrive, label = TRUE)

<<<<<<< HEAD
## ------------------------------------------------------------------------
meeting <- ymd_hms("2011-07-01 09:00:00", tz = "Pacific/Auckland")
with_tz(meeting, "America/Chicago")

## ------------------------------------------------------------------------
mistake <- force_tz(meeting, "America/Chicago")
with_tz(mistake, "Pacific/Auckland")

## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
meeting <- ymd_hms("2011-07-01 09:00:00", tz = "Pacific/Auckland")
with_tz(meeting, "America/Chicago")

## -----------------------------------------------------------------------------
mistake <- force_tz(meeting, "America/Chicago")
with_tz(mistake, "Pacific/Auckland")

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
auckland <- interval(arrive, leave) 
auckland
auckland <- arrive %--% leave
auckland

<<<<<<< HEAD
## ------------------------------------------------------------------------
jsm <- interval(ymd(20110720, tz = "Pacific/Auckland"), ymd(20110831, tz = "Pacific/Auckland"))
jsm

## ------------------------------------------------------------------------
int_overlaps(jsm, auckland)

## ------------------------------------------------------------------------
setdiff(auckland, jsm)

## ------------------------------------------------------------------------
minutes(2) ## period
dminutes(2) ## duration

## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
jsm <- interval(ymd(20110720, tz = "Pacific/Auckland"), ymd(20110831, tz = "Pacific/Auckland"))
jsm

## -----------------------------------------------------------------------------
int_overlaps(jsm, auckland)

## -----------------------------------------------------------------------------
setdiff(auckland, jsm)

## -----------------------------------------------------------------------------
minutes(2) ## period
dminutes(2) ## duration

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
leap_year(2011) ## regular year
ymd(20110101) + dyears(1)
ymd(20110101) + years(1)

leap_year(2012) ## leap year
ymd(20120101) + dyears(1)
ymd(20120101) + years(1)

<<<<<<< HEAD
## ------------------------------------------------------------------------
meetings <- meeting + weeks(0:5)

## ------------------------------------------------------------------------
meetings %within% jsm

## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
meetings <- meeting + weeks(0:5)

## -----------------------------------------------------------------------------
meetings %within% jsm

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
auckland / ddays(1)
auckland / ddays(2)
auckland / dminutes(1)

<<<<<<< HEAD
## ------------------------------------------------------------------------
auckland %/% months(1)
auckland %% months(1)

## ------------------------------------------------------------------------
as.period(auckland %% months(1))
as.period(auckland)

## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
auckland %/% months(1)
auckland %% months(1)

## -----------------------------------------------------------------------------
as.period(auckland %% months(1))
as.period(auckland)

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
jan31 <- ymd("2013-01-31")
jan31 + months(0:11)
floor_date(jan31, "month") + months(0:11) + days(31)
jan31 %m+% months(0:11)

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
last_day <- function(date) {
  ceiling_date(date, "month") - days(1)
}

