// Boost.Range library
//
//  Copyright Thorsten Ottosen 2003-2006. Use, modification and
//  distribution is subject to the Boost Software License, Version
//  1.0. (See accompanying file LICENSE_1_0.txt or copy at
//  http://www.boost.org/LICENSE_1_0.txt)
//
// For more information, see http://www.boost.org/libs/range/
//

#ifndef BOOST_RANGE_DISTANCE_HPP
#define BOOST_RANGE_DISTANCE_HPP

#if defined(_MSC_VER)
# pragma once
#endif

<<<<<<< HEAD
=======
#include <boost/iterator/distance.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/range/begin.hpp>
#include <boost/range/end.hpp>
#include <boost/range/difference_type.hpp>

<<<<<<< HEAD
namespace boost 
{

    template< class T >
    inline BOOST_DEDUCED_TYPENAME range_difference<T>::type 
    distance( const T& r )
    {
        return std::distance( boost::begin( r ), boost::end( r ) );
    }

=======
namespace boost
{

    namespace range_distance_adl_barrier
    {
        template< class T >
        inline BOOST_CXX14_CONSTEXPR BOOST_DEDUCED_TYPENAME range_difference<T>::type
        distance( const T& r )
        {
            return boost::iterators::distance( boost::begin( r ), boost::end( r ) );
        }
    }

    using namespace range_distance_adl_barrier;

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
} // namespace 'boost'

#endif
