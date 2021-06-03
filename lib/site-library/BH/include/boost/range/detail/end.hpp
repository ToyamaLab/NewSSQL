// Boost.Range library
//
//  Copyright Thorsten Ottosen 2003-2004. Use, modification and
//  distribution is subject to the Boost Software License, Version
//  1.0. (See accompanying file LICENSE_1_0.txt or copy at
//  http://www.boost.org/LICENSE_1_0.txt)
//
// For more information, see http://www.boost.org/libs/range/
//

#ifndef BOOST_RANGE_DETAIL_END_HPP
#define BOOST_RANGE_DETAIL_END_HPP

#include <boost/config.hpp> // BOOST_MSVC
#include <boost/detail/workaround.hpp>

#include <boost/range/detail/implementation_help.hpp>
#include <boost/range/iterator.hpp>
#include <boost/range/detail/common.hpp>

namespace boost
{
    namespace range_detail
    {
        template< typename T >
        struct range_end;

        //////////////////////////////////////////////////////////////////////
        // default
        //////////////////////////////////////////////////////////////////////

        template<>
        struct range_end<std_container_>
        {
            template< typename C >
<<<<<<< HEAD
            static BOOST_RANGE_DEDUCED_TYPENAME range_iterator<C>::type
=======
            BOOST_CONSTEXPR static BOOST_RANGE_DEDUCED_TYPENAME range_iterator<C>::type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            fun( C& c )
            {
                return c.end();
            };
        };

        //////////////////////////////////////////////////////////////////////
        // pair
        //////////////////////////////////////////////////////////////////////

        template<>
        struct range_end<std_pair_>
        {
            template< typename P >
<<<<<<< HEAD
            static BOOST_RANGE_DEDUCED_TYPENAME range_iterator<P>::type
=======
            BOOST_CONSTEXPR static BOOST_RANGE_DEDUCED_TYPENAME range_iterator<P>::type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            fun( const P& p )
            {
                return p.second;
            }
        };

        //////////////////////////////////////////////////////////////////////
        // array
        //////////////////////////////////////////////////////////////////////

        template<>
        struct range_end<array_>
        {
            template<typename T>
<<<<<<< HEAD
            static BOOST_RANGE_DEDUCED_TYPENAME remove_extent<T>::type* fun(T& t)
=======
            BOOST_CONSTEXPR static BOOST_RANGE_DEDUCED_TYPENAME remove_extent<T>::type* fun(T& t)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            {
                return t + remove_extent<T>::size;
            }
        };

    } // namespace 'range_detail'

    namespace range_adl_barrier
    {
        template< typename C >
<<<<<<< HEAD
        inline BOOST_RANGE_DEDUCED_TYPENAME range_iterator<C>::type
=======
        BOOST_CONSTEXPR inline BOOST_RANGE_DEDUCED_TYPENAME range_iterator<C>::type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        end( C& c )
        {
            return range_detail::range_end< BOOST_RANGE_DEDUCED_TYPENAME range_detail::range<C>::type >::fun( c );
        }
    } // namespace range_adl_barrier

} // namespace 'boost'

#endif
