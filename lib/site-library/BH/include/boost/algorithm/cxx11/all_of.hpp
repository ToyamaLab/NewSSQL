/* 
   Copyright (c) Marshall Clow 2008-2012.

   Distributed under the Boost Software License, Version 1.0. (See accompanying
   file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
*/

/// \file  all_of.hpp
/// \brief Test ranges to see if all elements match a value or predicate.
/// \author Marshall Clow

#ifndef BOOST_ALGORITHM_ALL_OF_HPP
#define BOOST_ALGORITHM_ALL_OF_HPP

<<<<<<< HEAD
=======
#include <boost/config.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/range/begin.hpp>
#include <boost/range/end.hpp>

namespace boost { namespace algorithm {

/// \fn all_of ( InputIterator first, InputIterator last, Predicate p )
/// \return true if all elements in [first, last) satisfy the predicate 'p'
/// \note returns true on an empty range
/// 
/// \param first The start of the input sequence
/// \param last  One past the end of the input sequence
/// \param p     A predicate for testing the elements of the sequence
///
/// \note           This function is part of the C++2011 standard library.
template<typename InputIterator, typename Predicate> 
<<<<<<< HEAD
bool all_of ( InputIterator first, InputIterator last, Predicate p )
=======
BOOST_CXX14_CONSTEXPR bool all_of ( InputIterator first, InputIterator last, Predicate p )
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    for ( ; first != last; ++first )
        if ( !p(*first)) 
            return false;
    return true; 
} 

/// \fn all_of ( const Range &r, Predicate p )
/// \return true if all elements in the range satisfy the predicate 'p'
/// \note returns true on an empty range
/// 
/// \param r    The input range
/// \param p    A predicate for testing the elements of the range
///
template<typename Range, typename Predicate> 
<<<<<<< HEAD
bool all_of ( const Range &r, Predicate p )
=======
BOOST_CXX14_CONSTEXPR bool all_of ( const Range &r, Predicate p )
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    return boost::algorithm::all_of ( boost::begin (r), boost::end (r), p );
} 

/// \fn all_of_equal ( InputIterator first, InputIterator last, const T &val )
/// \return true if all elements in [first, last) are equal to 'val'
/// \note returns true on an empty range
/// 
/// \param first The start of the input sequence
/// \param last  One past the end of the input sequence
/// \param val   A value to compare against
///
template<typename InputIterator, typename T> 
<<<<<<< HEAD
bool all_of_equal ( InputIterator first, InputIterator last, const T &val )
=======
BOOST_CXX14_CONSTEXPR bool all_of_equal ( InputIterator first, InputIterator last, const T &val )
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    for ( ; first != last; ++first )
    if ( val != *first ) 
        return false;
    return true; 
} 

/// \fn all_of_equal ( const Range &r, const T &val )
/// \return true if all elements in the range are equal to 'val'
/// \note returns true on an empty range
/// 
/// \param r    The input range
/// \param val  A value to compare against
///
template<typename Range, typename T> 
<<<<<<< HEAD
bool all_of_equal ( const Range &r, const T &val ) 
=======
BOOST_CXX14_CONSTEXPR bool all_of_equal ( const Range &r, const T &val ) 
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    return boost::algorithm::all_of_equal ( boost::begin (r), boost::end (r), val );
} 

}} // namespace boost and algorithm

#endif // BOOST_ALGORITHM_ALL_OF_HPP
