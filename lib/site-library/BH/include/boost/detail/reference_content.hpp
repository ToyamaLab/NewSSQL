//-----------------------------------------------------------------------------
// boost detail/reference_content.hpp header file
// See http://www.boost.org for updates, documentation, and revision history.
//-----------------------------------------------------------------------------
//
// Copyright (c) 2003
// Eric Friedman
//
// Distributed under the Boost Software License, Version 1.0. (See
// accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_DETAIL_REFERENCE_CONTENT_HPP
#define BOOST_DETAIL_REFERENCE_CONTENT_HPP

#include "boost/config.hpp"

<<<<<<< HEAD
#   include "boost/mpl/bool.hpp"
#   include "boost/type_traits/has_nothrow_copy.hpp"

#include "boost/mpl/void.hpp"

=======
#   include "boost/type_traits/integral_constant.hpp"
#   include "boost/type_traits/has_nothrow_copy.hpp"

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
namespace boost {

namespace detail {

<<<<<<< HEAD
=======
struct void_type {};

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
///////////////////////////////////////////////////////////////////////////////
// (detail) class template reference_content
//
// Non-Assignable wrapper for references.
//
template <typename RefT>
class reference_content
{
private: // representation

    RefT content_;

public: // structors

    ~reference_content()
    {
    }

    reference_content(RefT r)
        : content_( r )
    {
    }

    reference_content(const reference_content& operand)
        : content_( operand.content_ )
    {
    }

private: // non-Assignable

    reference_content& operator=(const reference_content&);

public: // queries

    RefT get() const
    {
        return content_;
    }

};

///////////////////////////////////////////////////////////////////////////////
// (detail) metafunction make_reference_content
//
// Wraps with reference_content if specified type is reference.
//

<<<<<<< HEAD
template <typename T = mpl::void_> struct make_reference_content;
=======
template <typename T = void_type> struct make_reference_content;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce


template <typename T>
struct make_reference_content
{
    typedef T type;
};

template <typename T>
struct make_reference_content< T& >
{
    typedef reference_content<T&> type;
};


template <>
<<<<<<< HEAD
struct make_reference_content< mpl::void_ >
=======
struct make_reference_content< void_type >
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    template <typename T>
    struct apply
        : make_reference_content<T>
    {
    };

<<<<<<< HEAD
    typedef mpl::void_ type;
=======
    typedef void_type type;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};

} // namespace detail

///////////////////////////////////////////////////////////////////////////////
// reference_content<T&> type traits specializations
//


template <typename T>
struct has_nothrow_copy<
      ::boost::detail::reference_content< T& >
    >
<<<<<<< HEAD
    : mpl::true_
=======
    : boost::true_type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
};


} // namespace boost

#endif // BOOST_DETAIL_REFERENCE_CONTENT_HPP
