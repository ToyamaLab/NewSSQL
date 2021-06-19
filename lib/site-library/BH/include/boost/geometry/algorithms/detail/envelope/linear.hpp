// Boost.Geometry (aka GGL, Generic Geometry Library)

// Copyright (c) 2007-2015 Barend Gehrels, Amsterdam, the Netherlands.
// Copyright (c) 2008-2015 Bruno Lalande, Paris, France.
// Copyright (c) 2009-2015 Mateusz Loskot, London, UK.

<<<<<<< HEAD
// This file was modified by Oracle on 2015, 2016.
// Modifications copyright (c) 2015-2016, Oracle and/or its affiliates.

// Contributed and/or modified by Vissarion Fysikopoulos, on behalf of Oracle
// Contributed and/or modified by Menelaos Karavelas, on behalf of Oracle
=======
// This file was modified by Oracle on 2015, 2016, 2018.
// Modifications copyright (c) 2015-2018, Oracle and/or its affiliates.

// Contributed and/or modified by Vissarion Fysikopoulos, on behalf of Oracle
// Contributed and/or modified by Menelaos Karavelas, on behalf of Oracle
// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

// Distributed under the Boost Software License, Version 1.0.
// (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_GEOMETRY_ALGORITHMS_DETAIL_ENVELOPE_LINEAR_HPP
#define BOOST_GEOMETRY_ALGORITHMS_DETAIL_ENVELOPE_LINEAR_HPP

<<<<<<< HEAD
#include <boost/geometry/core/cs.hpp>
#include <boost/geometry/core/tags.hpp>

#include <boost/geometry/iterators/segment_iterator.hpp>
=======

#include <boost/geometry/core/tags.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#include <boost/geometry/algorithms/detail/envelope/range.hpp>

#include <boost/geometry/algorithms/dispatch/envelope.hpp>

<<<<<<< HEAD
=======
// For backward compatibility
#include <boost/geometry/strategies/cartesian/envelope.hpp>
#include <boost/geometry/strategies/spherical/envelope.hpp>
#include <boost/geometry/strategies/geographic/envelope.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace boost { namespace geometry
{

<<<<<<< HEAD
#ifndef DOXYGEN_NO_DETAIL
namespace detail { namespace envelope
{


struct envelope_linestring_on_spheroid
{
    template <typename Linestring, typename Box, typename Strategy>
    static inline void apply(Linestring const& linestring,
                             Box& mbr,
                             Strategy const& strategy)
    {
        envelope_range::apply(geometry::segments_begin(linestring),
                              geometry::segments_end(linestring),
                              mbr,
                              strategy);
    }
};


}} // namespace detail::envelope
#endif // DOXYGEN_NO_DETAIL


=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#ifndef DOXYGEN_NO_DISPATCH
namespace dispatch
{


<<<<<<< HEAD
template <typename Linestring, typename CS_Tag>
struct envelope<Linestring, linestring_tag, CS_Tag>
    : detail::envelope::envelope_range
{};

template <typename Linestring>
struct envelope<Linestring, linestring_tag, spherical_equatorial_tag>
    : detail::envelope::envelope_linestring_on_spheroid
{};

template <typename Linestring>
struct envelope<Linestring, linestring_tag, geographic_tag>
    : detail::envelope::envelope_linestring_on_spheroid
{};


template <typename MultiLinestring, typename CS_Tag>
struct envelope
    <
        MultiLinestring, multi_linestring_tag, CS_Tag
    > : detail::envelope::envelope_multi_range
        <
            detail::envelope::envelope_range
        >
{};

template <typename MultiLinestring>
struct envelope
    <
        MultiLinestring, multi_linestring_tag, spherical_equatorial_tag
    > : detail::envelope::envelope_multi_range_on_spheroid
        <
            detail::envelope::envelope_linestring_on_spheroid
        >
{};

template <typename MultiLinestring>
struct envelope
    <
        MultiLinestring, multi_linestring_tag, geographic_tag
    > : detail::envelope::envelope_multi_range_on_spheroid
        <
            detail::envelope::envelope_linestring_on_spheroid
        >
{};
=======
template <typename Linestring>
struct envelope<Linestring, linestring_tag>
    : detail::envelope::envelope_range
{};


template <typename MultiLinestring>
struct envelope<MultiLinestring, multi_linestring_tag>
    : detail::envelope::envelope_multi_range
        <
            detail::envelope::envelope_range
        >
{};

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

} // namespace dispatch
#endif // DOXYGEN_NO_DISPATCH


}} // namespace boost::geometry

#endif // BOOST_GEOMETRY_ALGORITHMS_DETAIL_ENVELOPE_LINEAR_HPP
