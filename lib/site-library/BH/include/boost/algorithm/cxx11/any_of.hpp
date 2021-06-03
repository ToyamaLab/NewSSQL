/* 
   Copyright (c) Marshall Clow 2008-2012.

   Distributed under the Boost Software License, Version 1.0. (See accompanying
   file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)

    For more information, see http://www.boost.org
*/

/// \file
/// \brief Test ranges to see if any elements match a value or predicate.
/// \author Marshall Clow

#ifndef BOOST_ALGORITHM_ANY_OF_HPP
#define BOOST_ALGORITHM_ANY_OF_HPP

<<<<<<< HEAD
=======
#include <boost/config.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/range/begin.hpp>
#include <boost/range/end.hpp>

namespace boost { namespace algorithm {

/// \fn any_of ( InputIterator first, InputIterator last, Predicate p )
/// \return true if any of the elements in [first, last) satisfy the predicate
/// \note returns false on an empty range
/// 
/// \param first The start of the input sequence
/// \param last  One past the end of the input sequence
/// \param p     A predicate for testing the elements of the sequence
///
template<typename InputIterator, typename Predicate> 
<<<<<<< HEAD
bool any_of ( InputIterator first, InputIterator last, Predicate p ) 
=======
BOOST_CXX14_CONSTEXPR bool any_of ( InputIterator first, InputIterator last, Predicate p ) 
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    for ( ; first != last; ++first )
        if ( p(*first)) 
            return true;
    return false; 
} 

/// \fn any_of ( const Range &r, Predicate p )
/// \return true if any elements in the range satisfy the predicate 'p'
/// \note returns false on an empty range
/// 
/// \param r    The input range
/// \param p    A predicate for testing the elements of the range
///
template<typename Range, typename Predicate> 
<<<<<<< HEAD
bool any_of ( const Range &r, Predicate p )
=======
BOOST_CXX14_CONSTEXPR bool any_of ( const Range &r, Predicate p )
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    return boost::algorithm::any_of (boost::begin (r), boost::end (r), p);
} 

/// \fn any_of_equal ( InputIterator first, InputIterator last, const V &val )
/// \return true if any of the elements in [first, last) are equal to 'val'
/// \note returns false on an empty range
/// 
/// \param first The start of the input sequence
/// \param last  One past the end of the input sequence
/// \param val   A value to compare against
///
template<typename InputIterator, typename V> 
<<<<<<< HEAD
bool any_of_equal ( InputIterator first, InputIterator last, const V &val ) 
=======
BOOST_CXX14_CONSTEXPR bool any_of_equal ( InputIterator first, InputIterator last, const V &val ) 
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    for ( ; first != last; ++first )
        if ( val == *first )
            return true;
    return false; 
} 

/// \fn any_of_equal ( const Range &r, const V &val )
/// \return true if any of the elements in the range are equal to 'val'
/// \note returns false on an empty range
/// 
/// \param r     The input range
/// \param val   A value to compare against
///
template<typename Range, typename V> 
<<<<<<< HEAD
bool any_of_equal ( const Range &r, const V &val ) 
=======
BOOST_CXX14_CONSTEXPR bool any_of_equal ( const Range &r, const V &val ) 
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    return boost::algorithm::any_of_equal (boost::begin (r), boost::end (r), val);
}

}} // namespace boost and algorithm

#endif // BOOST_ALGORITHM_ANY_OF_HPP
