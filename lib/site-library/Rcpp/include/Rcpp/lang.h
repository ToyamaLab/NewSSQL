<<<<<<< HEAD
// -*- mode: C++; c-indent-level: 4; c-basic-offset: 4; tab-width: 8 -*-
//
// lang.h: Rcpp R/C++ interface class library -- extra lang_* functions
//
// Copyright (C) 2011 - 2013 Dirk Eddelbuettel and Romain Francois
=======

// lang.h: Rcpp R/C++ interface class library -- extra lang_* functions
//
// Copyright (C) 2011 - 2020 Dirk Eddelbuettel and Romain Francois
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

#ifndef Rcpp__lang_h
#define Rcpp__lang_h

#define Rcpp_list1 Rf_list1
#define Rcpp_lang1 Rf_lang1
<<<<<<< HEAD

namespace Rcpp {

inline SEXP Rcpp_lcons(SEXP car, SEXP cdr){
    Shield<SEXP> out( Rf_lcons( car, cdr ) );
    return out ;
}

inline SEXP Rcpp_list2( SEXP x0, SEXP x1 ){
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list1(x1)) );
    return out;
}

inline SEXP Rcpp_lang2( SEXP x0, SEXP x1 ){
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list1(x1)) );
    return out;
}





inline SEXP Rcpp_list3( SEXP x0, SEXP x1, SEXP x2 )
{
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list2(x1, x2)) );
    return out;
}

inline SEXP Rcpp_lang3( SEXP x0, SEXP x1, SEXP x2 )
{
    Shield<SEXP> out(Rf_lcons(x0, Rcpp_list2(x1, x2)) );
    return out;
}





inline SEXP Rcpp_list4( SEXP x0, SEXP x1, SEXP x2, SEXP x3 )
{
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list3(x1, x2, x3)) );

    return out;
}

inline SEXP Rcpp_lang4( SEXP x0, SEXP x1, SEXP x2, SEXP x3 )
{
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list3(x1, x2, x3)) );

    return out;
}





inline SEXP Rcpp_list5( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4 )
{
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list4(x1, x2, x3, x4)) );

    return out;
}

inline SEXP Rcpp_lang5( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4 )
{
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list4(x1, x2, x3, x4)) );

    return out;
=======
#define Rcpp_lang2 Rf_lang2
#define Rcpp_lang3 Rf_lang3
#define Rcpp_lang4 Rf_lang4
#define Rcpp_lang5 Rf_lang5
#define Rcpp_lang6 Rf_lang6

#define Rcpp_lcons Rf_lcons

namespace Rcpp {

inline SEXP Rcpp_list2(SEXP x0, SEXP x1) {
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list1(x1));
    UNPROTECT(1);
    return x0;
}

inline SEXP Rcpp_list3(SEXP x0, SEXP x1, SEXP x2) {
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list2(x1, x2));
    UNPROTECT(1);
    return x0;
}

inline SEXP Rcpp_list4(SEXP x0, SEXP x1, SEXP x2, SEXP x3) {
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list3(x1, x2, x3));
    UNPROTECT(1);
    return x0;
}

inline SEXP Rcpp_list5(SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4) {
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list4(x1, x2, x3, x4));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}



<<<<<<< HEAD


inline SEXP Rcpp_list6( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5 )
{
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list5(x1, x2, x3, x4, x5)) );

    return out;
}

inline SEXP Rcpp_lang6( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5 )
{
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list5(x1, x2, x3, x4, x5)) );

    return out;
=======
// `Rf_lang6()` is available on R 3.3, but `Rf_list6()` is not
inline SEXP Rcpp_list6( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5 )
{
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list5(x1, x2, x3, x4, x5));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}





inline SEXP Rcpp_list7( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list6(x1, x2, x3, x4, x5, x6)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list6(x1, x2, x3, x4, x5, x6));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

inline SEXP Rcpp_lang7( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list6(x1, x2, x3, x4, x5, x6)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_lcons(x0, Rcpp_list6(x1, x2, x3, x4, x5, x6));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}





inline SEXP Rcpp_list8( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list7(x1, x2, x3, x4, x5, x6, x7)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list7(x1, x2, x3, x4, x5, x6, x7));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

inline SEXP Rcpp_lang8( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list7(x1, x2, x3, x4, x5, x6, x7)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_lcons(x0, Rcpp_list7(x1, x2, x3, x4, x5, x6, x7));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}





inline SEXP Rcpp_list9( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list8(x1, x2, x3, x4, x5, x6, x7, x8)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list8(x1, x2, x3, x4, x5, x6, x7, x8));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

inline SEXP Rcpp_lang9( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list8(x1, x2, x3, x4, x5, x6, x7, x8)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_lcons(x0, Rcpp_list8(x1, x2, x3, x4, x5, x6, x7, x8));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}





inline SEXP Rcpp_list10( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list9(x1, x2, x3, x4, x5, x6, x7, x8, x9)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list9(x1, x2, x3, x4, x5, x6, x7, x8, x9));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

inline SEXP Rcpp_lang10( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list9(x1, x2, x3, x4, x5, x6, x7, x8, x9)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_lcons(x0, Rcpp_list9(x1, x2, x3, x4, x5, x6, x7, x8, x9));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}





inline SEXP Rcpp_list11( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list10(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list10(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

inline SEXP Rcpp_lang11( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list10(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_lcons(x0, Rcpp_list10(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}





inline SEXP Rcpp_list12( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list11(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list11(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

inline SEXP Rcpp_lang12( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list11(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_lcons(x0, Rcpp_list11(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}





inline SEXP Rcpp_list13( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11, SEXP x12 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list12(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list12(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

inline SEXP Rcpp_lang13( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11, SEXP x12 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list12(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_lcons(x0, Rcpp_list12(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}





inline SEXP Rcpp_list14( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11, SEXP x12, SEXP x13 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list13(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list13(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

inline SEXP Rcpp_lang14( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11, SEXP x12, SEXP x13 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list13(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_lcons(x0, Rcpp_list13(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}





inline SEXP Rcpp_list15( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11, SEXP x12, SEXP x13, SEXP x14 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list14(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list14(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

inline SEXP Rcpp_lang15( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11, SEXP x12, SEXP x13, SEXP x14 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list14(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_lcons(x0, Rcpp_list14(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}





inline SEXP Rcpp_list16( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11, SEXP x12, SEXP x13, SEXP x14, SEXP x15 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list15(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list15(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

inline SEXP Rcpp_lang16( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11, SEXP x12, SEXP x13, SEXP x14, SEXP x15 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list15(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_lcons(x0, Rcpp_list15(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}





inline SEXP Rcpp_list17( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11, SEXP x12, SEXP x13, SEXP x14, SEXP x15, SEXP x16 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list16(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list16(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

inline SEXP Rcpp_lang17( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11, SEXP x12, SEXP x13, SEXP x14, SEXP x15, SEXP x16 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list16(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_lcons(x0, Rcpp_list16(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}





inline SEXP Rcpp_list18( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11, SEXP x12, SEXP x13, SEXP x14, SEXP x15, SEXP x16, SEXP x17 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list17(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list17(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

inline SEXP Rcpp_lang18( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11, SEXP x12, SEXP x13, SEXP x14, SEXP x15, SEXP x16, SEXP x17 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list17(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_lcons(x0, Rcpp_list17(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}





inline SEXP Rcpp_list19( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11, SEXP x12, SEXP x13, SEXP x14, SEXP x15, SEXP x16, SEXP x17, SEXP x18 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list18(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list18(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

inline SEXP Rcpp_lang19( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11, SEXP x12, SEXP x13, SEXP x14, SEXP x15, SEXP x16, SEXP x17, SEXP x18 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list18(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_lcons(x0, Rcpp_list18(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}





inline SEXP Rcpp_list20( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11, SEXP x12, SEXP x13, SEXP x14, SEXP x15, SEXP x16, SEXP x17, SEXP x18, SEXP x19 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_cons(x0, Rcpp_list19(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x19)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_cons(x0, Rcpp_list19(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x19));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

inline SEXP Rcpp_lang20( SEXP x0, SEXP x1, SEXP x2, SEXP x3, SEXP x4, SEXP x5, SEXP x6, SEXP x7, SEXP x8, SEXP x9, SEXP x10, SEXP x11, SEXP x12, SEXP x13, SEXP x14, SEXP x15, SEXP x16, SEXP x17, SEXP x18, SEXP x19 )
{
<<<<<<< HEAD
    Shield<SEXP> out( Rf_lcons(x0, Rcpp_list19(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x19)) );

    return out;
=======
    PROTECT(x0);
    x0 = Rf_lcons(x0, Rcpp_list19(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x19));
    UNPROTECT(1);
    return x0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

}

#endif
<<<<<<< HEAD

=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
