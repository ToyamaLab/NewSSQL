// Copyright Kevlin Henney, 2000-2005.
// Copyright Alexander Nasonov, 2006-2010.
<<<<<<< HEAD
// Copyright Antony Polukhin, 2011-2014.
=======
// Copyright Antony Polukhin, 2011-2019.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
//
// Distributed under the Boost Software License, Version 1.0. (See
// accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)
//
// what:  lexical_cast custom keyword cast
// who:   contributed by Kevlin Henney,
//        enhanced with contributions from Terje Slettebo,
//        with additional fixes and suggestions from Gennaro Prota,
//        Beman Dawes, Dave Abrahams, Daryle Walker, Peter Dimov,
//        Alexander Nasonov, Antony Polukhin, Justin Viiret, Michael Hofmann,
//        Cheng Yang, Matthew Bradbury, David W. Birdsall, Pavel Korzh and other Boosters
// when:  November 2000, March 2003, June 2005, June 2006, March 2011 - 2014

#ifndef BOOST_LEXICAL_CAST_DETAIL_WIDEST_CHAR_HPP
#define BOOST_LEXICAL_CAST_DETAIL_WIDEST_CHAR_HPP

#include <boost/config.hpp>
#ifdef BOOST_HAS_PRAGMA_ONCE
#   pragma once
#endif

<<<<<<< HEAD
=======

#include <boost/type_traits/conditional.hpp>

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
namespace boost { namespace detail {

    template <typename TargetChar, typename SourceChar>
    struct widest_char {
<<<<<<< HEAD
        typedef BOOST_DEDUCED_TYPENAME boost::mpl::if_c<
=======
        typedef BOOST_DEDUCED_TYPENAME boost::conditional<
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            (sizeof(TargetChar) > sizeof(SourceChar))
            , TargetChar
            , SourceChar
        >::type type;
    };

}} // namespace boost::detail

#endif // BOOST_LEXICAL_CAST_DETAIL_WIDEST_CHAR_HPP

