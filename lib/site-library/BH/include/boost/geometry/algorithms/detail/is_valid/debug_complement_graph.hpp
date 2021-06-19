// Boost.Geometry (aka GGL, Generic Geometry Library)

<<<<<<< HEAD
// Copyright (c) 2014, Oracle and/or its affiliates.

// Contributed and/or modified by Menelaos Karavelas, on behalf of Oracle
=======
// Copyright (c) 2014, 2018, 2019, Oracle and/or its affiliates.

// Contributed and/or modified by Menelaos Karavelas, on behalf of Oracle
// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

// Licensed under the Boost Software License version 1.0.
// http://www.boost.org/users/license.html

#ifndef BOOST_GEOMETRY_ALGORITHMS_DETAIL_IS_VALID_DEBUG_COMPLEMENT_GRAPH_HPP
#define BOOST_GEOMETRY_ALGORITHMS_DETAIL_IS_VALID_DEBUG_COMPLEMENT_GRAPH_HPP

#ifdef BOOST_GEOMETRY_TEST_DEBUG
#include <iostream>
#endif

<<<<<<< HEAD
=======
#include <boost/geometry/algorithms/detail/is_valid/complement_graph.hpp>

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
namespace boost { namespace geometry
{

namespace detail { namespace is_valid
{


#ifdef BOOST_GEOMETRY_TEST_DEBUG
<<<<<<< HEAD
template <typename OutputStream, typename TurnPoint>
inline void
debug_print_complement_graph(OutputStream& os,
                             complement_graph<TurnPoint> const& graph)
=======
template <typename OutputStream, typename TurnPoint, typename CSTag>
inline void
debug_print_complement_graph(OutputStream& os,
                             complement_graph<TurnPoint, CSTag> const& graph)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    typedef typename complement_graph<TurnPoint>::vertex_handle vertex_handle;

    os << "num rings: " << graph.m_num_rings << std::endl;
    os << "vertex ids: {";
    for (vertex_handle it = graph.m_vertices.begin();
         it != graph.m_vertices.end(); ++it)
    {
        os << " " << it->id();
    }
    os << " }" << std::endl;        

    for (vertex_handle it = graph.m_vertices.begin();
         it != graph.m_vertices.end(); ++it)
    {
        os << "neighbors of " << it->id() << ": {";
        for (typename complement_graph
                 <
                     TurnPoint
                 >::neighbor_container::const_iterator
                 nit = graph.m_neighbors[it->id()].begin();
             nit != graph.m_neighbors[it->id()].end(); ++nit)
        {
            os << " " << (*nit)->id();
        }
        os << "}" << std::endl;        
    }
}
#else
<<<<<<< HEAD
template <typename OutputStream, typename TurnPoint>
inline void debug_print_complement_graph(OutputStream&,
                                         complement_graph<TurnPoint> const&)
=======
template <typename OutputStream, typename TurnPoint, typename CSTag>
inline void debug_print_complement_graph(OutputStream&,
                                         complement_graph<TurnPoint, CSTag> const&)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
}
#endif


}} // namespace detail::is_valid

}} // namespace boost::geometry

#endif // BOOST_GEOMETRY_ALGORITHMS_DETAIL_IS_VALID_COMPLEMENT_GRAPH_HPP
