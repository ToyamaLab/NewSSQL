<<<<<<< HEAD
// -*- mode: C++; c-indent-level: 4; c-basic-offset: 4; tab-width: 4 -*-
//
// config.h: Rcpp R/C++ interface class library -- Rcpp configuration
//
// Copyright (C) 2010 - 2018  Dirk Eddelbuettel and Romain Francois
=======

// config.h: Rcpp R/C++ interface class library -- Rcpp configuration
//
// Copyright (C) 2010 - 2020  Dirk Eddelbuettel and Romain Francois
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
//
// This file is part of Rcpp.
//
// Rcpp is free software: you can redistribute it and/or modify it
// under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 2 of the License, or
// (at your option) any later version.
//
// Rcpp is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with Rcpp.  If not, see <http://www.gnu.org/licenses/>.

#ifndef RCPP__CONFIG_H
#define RCPP__CONFIG_H

#define Rcpp_Version(v,p,s) (((v) * 65536) + ((p) * 256) + (s))

#define RcppDevVersion(maj, min, rev, dev)  (((maj)*1000000) + ((min)*10000) + ((rev)*100) + (dev))

// the currently released version
<<<<<<< HEAD
#define RCPP_VERSION Rcpp_Version(0,12,18)

// the current source snapshot
#define RCPP_DEV_VERSION RcppDevVersion(0,12,18,0)
=======
#define RCPP_VERSION            Rcpp_Version(1,0,5)
#define RCPP_VERSION_STRING     "1.0.5"

// the current source snapshot
#define RCPP_DEV_VERSION        RcppDevVersion(1,0,5,0)
#define RCPP_DEV_VERSION_STRING "1.0.5"
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#endif
