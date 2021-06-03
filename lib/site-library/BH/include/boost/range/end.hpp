// Boost.Range library
//
//  Copyright Thorsten Ottosen 2003-2004. Use, modification and
//  distribution is subject to the Boost Software License, Version
//  1.0. (See accompanying file LICENSE_1_0.txt or copy at
//  http://www.boost.org/LICENSE_1_0.txt)
//
// For more information, see http://www.boost.org/libs/range/
//

#ifndef BOOST_RANGE_END_HPP
#define BOOST_RANGE_END_HPP

#if defined(_MSC_VER)
# pragma once
#endif

#include <boost/range/config.hpp>

#ifdef BOOST_NO_FUNCTION_TEMPLATE_ORDERING
#include <boost/range/detail/end.hpp>
#else

#include <boost/range/detail/implementation_help.hpp>
#include <boost/range/iterator.hpp>
#include <boost/range/const_iterator.hpp>
<<<<<<< HEAD
=======
#include <boost/config.hpp>
#include <boost/config/workaround.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace boost
{

#if !BOOST_WORKAROUND(__BORLANDC__, BOOST_TESTED_AT(0x564))
namespace range_detail
{
#endif

        //////////////////////////////////////////////////////////////////////
        // primary template
        //////////////////////////////////////////////////////////////////////
        template< typename C >
<<<<<<< HEAD
        inline BOOST_DEDUCED_TYPENAME range_iterator<C>::type
=======
        BOOST_CONSTEXPR inline BOOST_DEDUCED_TYPENAME range_iterator<C>::type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        range_end( C& c )
        {
            //
            // If you get a compile-error here, it is most likely because
            // you have not implemented range_begin() properly in
            // the namespace of C
            //
            return c.end();
        }

        //////////////////////////////////////////////////////////////////////
        // pair
        //////////////////////////////////////////////////////////////////////

        template< typename Iterator >
<<<<<<< HEAD
        inline Iterator range_end( const std::pair<Iterator,Iterator>& p )
=======
        BOOST_CONSTEXPR inline Iterator range_end( const std::pair<Iterator,Iterator>& p )
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            return p.second;
        }

        template< typename Iterator >
<<<<<<< HEAD
        inline Iterator range_end( std::pair<Iterator,Iterator>& p )
=======
        BOOST_CONSTEXPR inline Iterator range_end( std::pair<Iterator,Iterator>& p )
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            return p.second;
        }

        //////////////////////////////////////////////////////////////////////
        // array
        //////////////////////////////////////////////////////////////////////

        template< typename T, std::size_t sz >
<<<<<<< HEAD
        inline const T* range_end( const T (&a)[sz] )
=======
        BOOST_CONSTEXPR inline const T* range_end( const T (&a)[sz] ) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            return range_detail::array_end<T,sz>( a );
        }

        template< typename T, std::size_t sz >
<<<<<<< HEAD
        inline T* range_end( T (&a)[sz] )
=======
        BOOST_CONSTEXPR inline T* range_end( T (&a)[sz] ) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            return range_detail::array_end<T,sz>( a );
        }

#if !BOOST_WORKAROUND(__BORLANDC__, BOOST_TESTED_AT(0x564))
} // namespace 'range_detail'
#endif

namespace range_adl_barrier
{

template< class T >
<<<<<<< HEAD
=======
#if !BOOST_WORKAROUND(BOOST_GCC, < 40700)
BOOST_CONSTEXPR
#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
inline BOOST_DEDUCED_TYPENAME range_iterator<T>::type end( T& r )
{
#if !BOOST_WORKAROUND(__BORLANDC__, BOOST_TESTED_AT(0x564))
    using namespace range_detail;
#endif
    return range_end( r );
}

template< class T >
<<<<<<< HEAD
=======
#if !BOOST_WORKAROUND(BOOST_GCC, < 40700)
BOOST_CONSTEXPR
#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
inline BOOST_DEDUCED_TYPENAME range_iterator<const T>::type end( const T& r )
{
#if !BOOST_WORKAROUND(__BORLANDC__, BOOST_TESTED_AT(0x564))
    using namespace range_detail;
#endif
    return range_end( r );
}

    } // namespace range_adl_barrier
} // namespace 'boost'

#endif // BOOST_NO_FUNCTION_TEMPLATE_ORDERING

namespace boost
{
    namespace range_adl_barrier
    {
        template< class T >
<<<<<<< HEAD
        inline BOOST_DEDUCED_TYPENAME range_iterator<const T>::type
=======
        BOOST_CONSTEXPR inline BOOST_DEDUCED_TYPENAME range_iterator<const T>::type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        const_end( const T& r )
        {
            return boost::range_adl_barrier::end( r );
        }
    } // namespace range_adl_barrier
    using namespace range_adl_barrier;
} // namespace boost

#endif

